package com.fzs.sula.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fzs.sula.api.model.Ambiente;
import com.fzs.sula.api.service.AmbienteService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/ambiente")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@SecurityRequirement(name = "bearerAuth")
public class AmbienteController {
    private AmbienteService ambienteService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAmbientes() throws JsonProcessingException {
        return ambienteService.getAmbientes();
    }

    @GetMapping(value = "/details/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAmbiente(@PathVariable(value = "id") Long id) throws JsonProcessingException {
        return ambienteService.getAmbiente(id);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createAmbinte (@Valid @RequestBody Ambiente ambiente){
        return ambienteService.createAmbinte(ambiente);
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateAmbiente (@PathVariable(value = "id") Long id, @Valid @RequestBody Ambiente ambiente){
        return ambienteService.updateAmbiente(ambiente, id);
    }
}
