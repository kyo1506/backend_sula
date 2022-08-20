package com.fzs.ads.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fzs.ads.api.model.Ambient;
import com.fzs.ads.api.service.AmbientService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/ambient")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@SecurityRequirement(name = "bearerAuth")
public class AmbientController {
    private AmbientService ambientService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> ambients() throws JsonProcessingException {
        return ambientService.ambients();
    }

    @GetMapping(value = "/details/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> ambientById(@PathVariable(value = "id") UUID id) throws JsonProcessingException {
        return ambientService.ambientById(id);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createAmbient(@Valid @RequestBody Ambient ambient) {
        return ambientService.createAmbient(ambient);
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateAmbient(@PathVariable(value = "id") UUID id, @Valid @RequestBody Ambient ambient) {
        return ambientService.updateAmbient(ambient, id);
    }
}
