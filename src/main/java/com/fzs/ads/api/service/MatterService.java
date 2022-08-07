package com.fzs.ads.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fzs.ads.api.model.Matter;
import com.fzs.ads.api.repository.MatterRepository;
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
public class MatterService {
    private final ObjectMapper objectMapper;
    private MatterRepository matterRepository;

    public ResponseEntity<Object> matters() throws JsonProcessingException {
        var mattersList = matterRepository.findAll();
        if (!mattersList.isEmpty()) {
            return new ResponseEntity<>(objectMapper.writeValueAsString(mattersList), HttpStatus.OK);
        } else return new ResponseEntity<>("There are no matters registered", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> matterById(UUID id) throws JsonProcessingException {
        var matter = matterRepository.findById(id);
        if (matter.isPresent()) {
            return new ResponseEntity<>(objectMapper.writeValueAsString(matter), HttpStatus.OK);
        } else return new ResponseEntity<>("Matter not found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> createMatter(Matter model) {
        if (!matterRepository.existsMatterByName(model.getName())) {
            var matter = new Matter();
            matter.setName(model.getName());
            matter.setSemester(model.getSemester());
            Matter matterSaved = matterRepository.save(matter);
            if (matterRepository.findById(matterSaved.getId()).isPresent())
                return new ResponseEntity<>("Matter successfully registered", HttpStatus.OK);
            else
                return new ResponseEntity<>("An error occurred during processing", HttpStatus.INTERNAL_SERVER_ERROR);
        } else return new ResponseEntity<>("There is already a matter with that name", HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public ResponseEntity<Object> updateMatter(Matter model, UUID id) {
        if (matterRepository.findById(id).isPresent()) {
            Matter matter = matterRepository.findById(id).get();
            matter.setName(model.getName());
            matter.setSemester(model.getSemester());
            matter.setIsActive(model.getIsActive());
            matter.setUpdatedOn(Timestamp.from(Instant.now()));
            Matter matterSaved = matterRepository.save(matter);
            if (matterRepository.findById(matterSaved.getId()).isPresent())
                return new ResponseEntity<>("Matter successfully updated", HttpStatus.OK);
            else
                return new ResponseEntity<>("An error occurred during processing", HttpStatus.INTERNAL_SERVER_ERROR);
        } else return new ResponseEntity<>("Matter not found", HttpStatus.NOT_FOUND);
    }
}
