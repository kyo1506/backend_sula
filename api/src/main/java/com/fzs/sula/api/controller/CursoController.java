package com.fzs.sula.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fzs.sula.api.model.Curso;
import com.fzs.sula.api.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/curso")
@RequiredArgsConstructor
public class CursoController {
    private final CursoService cursoService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getCursos() throws JsonProcessingException {
        return cursoService.getCursos();
    }

    @GetMapping(value = "/details/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getCurso(@PathVariable(value = "id") Long id) throws JsonProcessingException {
        return cursoService.getCurso(id);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createCurso (@Valid @RequestBody Curso curso){
        return cursoService.createCurso(curso);
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateCurso (@PathVariable(value = "id") Long id, @Valid @RequestBody Curso curso){
        return cursoService.updateCurso(curso, id);
    }
}
