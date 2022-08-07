package com.fzs.ads.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fzs.ads.api.model.Matter;
import com.fzs.ads.api.service.MatterService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/materia")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@SecurityRequirement(name = "bearerAuth")
public class MatterController {
    private MatterService matterService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> matters() throws JsonProcessingException {
        return matterService.matters();
    }

    @GetMapping(value = "/details/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> matterById(@PathVariable(value = "id") UUID id) throws JsonProcessingException {
        return matterService.matterById(id);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createMatter(@Valid @RequestBody Matter matter) {
        return matterService.createMatter(matter);
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateMatter(@PathVariable(value = "id") UUID id, @Valid @RequestBody Matter matter) {
        return matterService.updateMatter(matter, id);
    }
}
