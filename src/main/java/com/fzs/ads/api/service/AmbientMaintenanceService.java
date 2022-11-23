package com.fzs.ads.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fzs.ads.api.model.AmbientMaintenance;
import com.fzs.ads.api.repository.AmbientMaintenanceRepository;
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
public class AmbientMaintenanceService {
    private final ObjectMapper objectMapper;
    private AmbientMaintenanceRepository ambientMaintenanceRepository;

    public ResponseEntity<Object> ambientMaintenances() throws JsonProcessingException {
        var ambientMaintenancesList = ambientMaintenanceRepository.findAll();
        if (!ambientMaintenancesList.isEmpty()) {
            return new ResponseEntity<>(objectMapper.writeValueAsString(ambientMaintenancesList), HttpStatus.OK);
        } else return new ResponseEntity<>("There are no maintenances registered", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> ambientMaintenancesByAmbienteId(UUID id) throws JsonProcessingException {
        var ambientMaintenancesList = ambientMaintenanceRepository.findAllByAmbient_Id(id);
        if (!ambientMaintenancesList.isEmpty()) {
            return new ResponseEntity<>(objectMapper.writeValueAsString(ambientMaintenancesList), HttpStatus.OK);
        } else return new ResponseEntity<>("Maintenances not found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> ambientMaintenancesById(UUID id) throws JsonProcessingException {
        var ambientMaintenance = ambientMaintenanceRepository.findById(id);
        if (ambientMaintenance.isPresent()) {
            return new ResponseEntity<>(objectMapper.writeValueAsString(ambientMaintenance), HttpStatus.OK);
        } else return new ResponseEntity<>("Maintenance not found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> createAmbientMaintenance(AmbientMaintenance model) {
        if (ambientMaintenanceRepository.findAmbientMaintenanceByAmbient_IdAndStartDateAndEndDateAndIsDone(
                model.getAmbient().getId(),
                model.getStartDate(),
                model.getEndDate(),
                false).isEmpty()) {
            AmbientMaintenance ambientMaintenance = new AmbientMaintenance();
            ambientMaintenance.setAmbient(model.getAmbient());
            ambientMaintenance.setStartDate(model.getStartDate());
            ambientMaintenance.setEndDate(model.getEndDate());
            var ambientMaintenanceSaved = ambientMaintenanceRepository.save(ambientMaintenance);
            if (ambientMaintenanceRepository.findById(ambientMaintenanceSaved.getId()).isPresent())
                return new ResponseEntity<>("Maintenance scheduled successfully", HttpStatus.OK);
            else
                return new ResponseEntity<>("An error occurred during processing", HttpStatus.INTERNAL_SERVER_ERROR);
        } else
            return new ResponseEntity<>("There is already a maintenance scheduled for this room at this time", HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public ResponseEntity<Object> updateAmbientMaintenances(AmbientMaintenance model, UUID id) {
        if (ambientMaintenanceRepository.findById(id).isPresent()) {
            var ambientMaintenance = ambientMaintenanceRepository.findById(id).get();
            ambientMaintenance.setAmbient(model.getAmbient());
            ambientMaintenance.setStartDate(model.getStartDate());
            ambientMaintenance.setEndDate(model.getEndDate());
            ambientMaintenance.setIsDone(model.getIsDone());
            ambientMaintenance.setUpdatedOn(Timestamp.from(Instant.now()));
            AmbientMaintenance ambientMaintenanceSaved = ambientMaintenanceRepository.save(ambientMaintenance);
            if (ambientMaintenanceRepository.findById(ambientMaintenanceSaved.getId()).isPresent())
                return new ResponseEntity<>("Maintenance updated successfully", HttpStatus.OK);
            else
                return new ResponseEntity<>("An error occurred during processing", HttpStatus.INTERNAL_SERVER_ERROR);
        } else return new ResponseEntity<>("Maintenance not found", HttpStatus.NOT_FOUND);
    }
}
