package com.fzs.sula.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fzs.sula.api.enums.Semestre;
import com.fzs.sula.api.model.Agendamento;
import com.fzs.sula.api.service.AgendamentoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/agendamento")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@SecurityRequirement(name = "bearerAuth")
public class AgendamentoController {
    private AgendamentoService agendamentoService;

    @GetMapping(value = "/all/{id}/{semestre}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getagendamentosCursoSemestre(@PathVariable(value = "id") Long id,
                                                               @PathVariable(value = "semestre") Semestre semestre) throws JsonProcessingException {
        return agendamentoService.getagendamentosCursoSemestre(id, semestre);
    }

    @GetMapping(value = "/all/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAgendamentosUserId(@PathVariable(value = "id") Long id) throws JsonProcessingException {
        return agendamentoService.getAgendamentosUserId(id);
    }

    @GetMapping(value = "/details/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAgendamento(@PathVariable(value = "id") Long id) throws JsonProcessingException {
        return agendamentoService.getAgendamento(id);
    }

    @PostMapping(value = "/create/unico", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createAgendamentoUnico (@Valid @RequestBody Agendamento agendamento){
        return agendamentoService.createAgendamentoUnico(agendamento);
    }

    @PostMapping(value = "/create/recorrente", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createAgendamentoRecorrente (@Valid @RequestBody List<Agendamento> agendamentos){
        return agendamentoService.createAgendamentoRecorrente(agendamentos);
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateAgendamento (@PathVariable(value = "id") Long id, @Valid @RequestBody Agendamento agendamento){
        return agendamentoService.updateAgendamento(agendamento, id);
    }
}
