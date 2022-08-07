package com.fzs.ads.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fzs.ads.api.model.AmbientMaintenance;
import com.fzs.ads.api.service.AmbientMaintenanceService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/ambientMaintenance")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@SecurityRequirement(name = "bearerAuth")
public class AmbientMaintenanceController {
    private AmbientMaintenanceService ambientMaintenanceService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> ambientMaintenances() throws JsonProcessingException {
        return ambientMaintenanceService.ambientMaintenances();
    }

    @GetMapping(value = "/all/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> ambientMaintenancesByAmbienteId(@PathVariable(value = "id") UUID id) throws JsonProcessingException {
        return ambientMaintenanceService.ambientMaintenancesByAmbienteId(id);
    }

    @GetMapping(value = "/details/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> ambientMaintenancesById(@PathVariable(value = "id") UUID id) throws JsonProcessingException {
        return ambientMaintenanceService.ambientMaintenancesById(id);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createAmbientMaintenance(@Valid @RequestBody AmbientMaintenance ambientMaintenance) {
        return ambientMaintenanceService.createAmbientMaintenance(ambientMaintenance);
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateAmbientMaintenances(@PathVariable(value = "id") UUID id, @Valid @RequestBody AmbientMaintenance ambientMaintenance) {
        return ambientMaintenanceService.updateAmbientMaintenances(ambientMaintenance, id);
    }
}
