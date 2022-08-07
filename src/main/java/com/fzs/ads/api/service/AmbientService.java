package com.fzs.ads.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fzs.ads.api.model.Ambient;
import com.fzs.ads.api.repository.AmbientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AmbientService {
    private final ObjectMapper objectMapper;
    private AmbientRepository ambientRepository;

    public ResponseEntity<Object> ambients() throws JsonProcessingException {
        var ambientsList = ambientRepository.findAll();
        if (!ambientsList.isEmpty()) {
            return new ResponseEntity<>(objectMapper.writeValueAsString(ambientsList), HttpStatus.OK);
        } else return new ResponseEntity<>("There are no registered ambients", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> ambientById(UUID id) throws JsonProcessingException {
        var ambient = ambientRepository.findById(id);
        if (ambient.isPresent()) {
            return new ResponseEntity<>(objectMapper.writeValueAsString(ambient), HttpStatus.OK);
        } else return new ResponseEntity<>("Ambient not found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> createAmbient(Ambient model) {
        if (!ambientRepository.existsAmbientByName(model.getName())) {
            var ambient = new Ambient();
            ambient.setName(model.getName());
            ambient.setType(model.getType());
            ambient.setCharacteristics(model.getCharacteristics());
            ambient.setAvailabilities(model.getAvailabilities());
            ambient.setReference(model.getReference());
            ambient.setNumber(model.getNumber());
            Ambient ambientSaved = ambientRepository.save(ambient);
            if (ambientRepository.findById(ambientSaved.getId()).isPresent())
                return new ResponseEntity<>("Ambient successfully registered", HttpStatus.OK);
            else
                return new ResponseEntity<>("An error occurred during processing", HttpStatus.INTERNAL_SERVER_ERROR);
        } else return new ResponseEntity<>("An ambient with that name already exists", HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public ResponseEntity<Object> updateAmbient(Ambient model, UUID id) {
        if (ambientRepository.findById(id).isPresent()) {
            Ambient ambient = ambientRepository.findById(id).get();
            ambient.setName(model.getName());
            ambient.setIsActive(model.getIsActive());
            ambient.setType(model.getType());
            ambient.setCharacteristics(model.getCharacteristics());
            ambient.setAvailabilities(model.getAvailabilities());
            ambient.setReference(model.getReference());
            ambient.setNumber(model.getNumber());
            ambient.setUpdatedOn(Timestamp.from(Instant.now()));
            Ambient ambientSalvo = ambientRepository.save(ambient);
            if (ambientRepository.findById(ambientSalvo.getId()).isPresent())
                return new ResponseEntity<>("Environment successfully updated", HttpStatus.OK);
            else
                return new ResponseEntity<>("An error occurred during processing", HttpStatus.INTERNAL_SERVER_ERROR);
        } else return new ResponseEntity<>("Ambient not found", HttpStatus.NOT_FOUND);
    }
}
