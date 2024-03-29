package com.fzs.ads.api.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fzs.ads.api.model.Role;
import com.fzs.ads.api.model.User;
import com.fzs.ads.api.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty())
            throw new UsernameNotFoundException("Username not found");

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.get().getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

        return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(), authorities);
    }

    public ResponseEntity<Object> users() throws JsonProcessingException {
        List<User> usersList = userRepository.findAll();
        if (!usersList.isEmpty()) {
            return new ResponseEntity<>(objectMapper.writeValueAsString(usersList), HttpStatus.OK);
        } else return new ResponseEntity<>("There are no registered users", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> userById(UUID id) throws JsonProcessingException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(objectMapper.writeValueAsString(user), HttpStatus.OK);
        } else return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> userByUsername(String username) throws JsonProcessingException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return new ResponseEntity<>(objectMapper.writeValueAsString(user), HttpStatus.OK);
        } else return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> createUser(User model) {
        if (!userRepository.existsByUsername(model.getUsername())) {
            User user = new User();
            user.setName(model.getName());
            user.setEmail(model.getEmail());
            user.setUsername(model.getUsername());
            user.setPassword(passwordEncoder.encode(model.getPassword()));
            user.setRoles(model.getRoles());
            User userSalvo = userRepository.save(user);
            if (userRepository.findById(userSalvo.getId()).isPresent())
                return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
            else return new ResponseEntity<>("An error occurred during processing", HttpStatus.INTERNAL_SERVER_ERROR);
        } else
            return new ResponseEntity<>("A record with that username already exists", HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public ResponseEntity<Object> updateUser(User model, UUID id) {
        if (userRepository.findById(id).isPresent()) {
            var user = userRepository.findById(id).get();
            user.setName(model.getName());
            user.setEmail(model.getEmail());
            user.setUsername(model.getUsername());
            if (!user.getPassword().equals(model.getPassword()))
                user.setPassword(passwordEncoder.encode(model.getPassword()));
            user.setRoles(model.getRoles());
            user.setUpdatedOn(Timestamp.from(Instant.now()));
            User userSalvo = userRepository.save(user);
            if (userRepository.findById(userSalvo.getId()).isPresent())
                return new ResponseEntity<>("User successfully updated", HttpStatus.OK);
            else return new ResponseEntity<>("An error occurred during processing", HttpStatus.INTERNAL_SERVER_ERROR);
        } else return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes(StandardCharsets.UTF_8));
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                Optional<User> user = userRepository.findByUsername(username);
                String access_token = JWT.create()
                        .withSubject(user.get().getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.get().getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}
