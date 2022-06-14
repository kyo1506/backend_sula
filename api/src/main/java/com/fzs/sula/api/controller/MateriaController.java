package com.fzs.sula.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fzs.sula.api.model.Materia;
import com.fzs.sula.api.service.MateriaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/materia")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class MateriaController {
    private final MateriaService materiaService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getMaterias() throws JsonProcessingException {
        return materiaService.getMaterias();
    }

    @GetMapping(value = "/details/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getMateria(@PathVariable(value = "id") Long id) throws JsonProcessingException {
        return materiaService.getMateria(id);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createMateria (@Valid @RequestBody Materia materia){
        return materiaService.createMateria(materia);
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateMateria (@PathVariable(value = "id") Long id, @Valid @RequestBody Materia materia){
        return materiaService.updateMateria(materia, id);
    }
}
