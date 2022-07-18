package com.fzs.sula.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fzs.sula.api.model.User;
import com.fzs.sula.api.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/user")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private UserService userService;

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUsers() throws JsonProcessingException {
        return userService.getUsers();
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping(value = "/details/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUser(@PathVariable(value = "id") Long id) throws JsonProcessingException {
        return userService.getUser(id);
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping(value = "/details/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUser(@PathVariable(value = "username") String username) throws JsonProcessingException {
        return userService.getUser(username);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
       return userService.createUser(user);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateUser (@PathVariable(value = "id") Long id, @Valid @RequestBody User user){
        return userService.updateUser(user, id);
    }

    @GetMapping(value = "/refresh/token", produces = MediaType.APPLICATION_JSON_VALUE)
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        userService.refreshToken(request, response);
    }
}
