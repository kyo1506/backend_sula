package com.fzs.ads.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fzs.ads.api.model.Subject;
import com.fzs.ads.api.repository.SubjectRepository;
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
public class SubjectService {
    private final ObjectMapper objectMapper;
    private SubjectRepository subjectRepository;

    public ResponseEntity<Object> subjects() throws JsonProcessingException {
        var subjectsList = subjectRepository.findAll();
        if (!subjectsList.isEmpty()) {
            return new ResponseEntity<>(objectMapper.writeValueAsString(subjectsList), HttpStatus.OK);
        } else return new ResponseEntity<>("There are no subjects registered", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> subjectById(UUID id) throws JsonProcessingException {
        var subject = subjectRepository.findById(id);
        if (subject.isPresent()) {
            return new ResponseEntity<>(objectMapper.writeValueAsString(subject), HttpStatus.OK);
        } else return new ResponseEntity<>("Subject not found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> createSubject(Subject model) {
        if (!subjectRepository.existsSubjectByName(model.getName())) {
            var subject = new Subject();
            subject.setName(model.getName());
            subject.setSemester(model.getSemester());
            Subject subjectSaved = subjectRepository.save(subject);
            if (subjectRepository.findById(subjectSaved.getId()).isPresent())
                return new ResponseEntity<>("Subject successfully registered", HttpStatus.OK);
            else
                return new ResponseEntity<>("An error occurred during processing", HttpStatus.INTERNAL_SERVER_ERROR);
        } else return new ResponseEntity<>("There is already a subject with that name", HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public ResponseEntity<Object> updateSubject(Subject model, UUID id) {
        if (subjectRepository.findById(id).isPresent()) {
            Subject subject = subjectRepository.findById(id).get();
            subject.setName(model.getName());
            subject.setSemester(model.getSemester());
            subject.setIsActive(model.getIsActive());
            subject.setUpdatedOn(Timestamp.from(Instant.now()));
            Subject subjectSaved = subjectRepository.save(subject);
            if (subjectRepository.findById(subjectSaved.getId()).isPresent())
                return new ResponseEntity<>("Subject successfully updated", HttpStatus.OK);
            else
                return new ResponseEntity<>("An error occurred during processing", HttpStatus.INTERNAL_SERVER_ERROR);
        } else return new ResponseEntity<>("Subject not found", HttpStatus.NOT_FOUND);
    }
}
