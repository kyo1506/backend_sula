package com.fzs.sula.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fzs.sula.api.model.ManutencaoAmbiente;
import com.fzs.sula.api.service.ManutencaoAmbienteService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/manutencaoAmbiente")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@SecurityRequirement(name = "bearerAuth")
public class ManutencaoAmbienteController {
    private ManutencaoAmbienteService manutencaoAmbienteService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getManutencoesAmbiente() throws JsonProcessingException {
        return manutencaoAmbienteService.getManutencoesAmbiente();
    }

    @GetMapping(value = "/all/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getManutencoesAmbienteAmbienteId(@PathVariable(value = "id") Long id) throws JsonProcessingException {
        return manutencaoAmbienteService.getManutencoesAmbienteAmbienteId(id);
    }

    @GetMapping(value = "/details/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getManutencaoAmbiente(@PathVariable(value = "id") Long id) throws JsonProcessingException {
        return manutencaoAmbienteService.getManutencaoAmbiente(id);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createManutencaoAmbiente (@Valid @RequestBody ManutencaoAmbiente manutencaoAmbiente){
        return manutencaoAmbienteService.createManutencaoAmbiente(manutencaoAmbiente);
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateCurso (@PathVariable(value = "id") Long id, @Valid @RequestBody ManutencaoAmbiente manutencaoAmbiente){
        return manutencaoAmbienteService.updateManutencaoAmbiente(manutencaoAmbiente, id);
    }
}
