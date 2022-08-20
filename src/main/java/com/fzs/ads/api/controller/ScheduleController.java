package com.fzs.ads.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fzs.ads.api.enums.ESubjectSemester;
import com.fzs.ads.api.model.Schedule;
import com.fzs.ads.api.service.ScheduleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/schedule")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@SecurityRequirement(name = "bearerAuth")
public class ScheduleController {
    private ScheduleService scheduleService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> schedules() throws JsonProcessingException {
        return scheduleService.schedules();
    }

    @GetMapping(value = "/all/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> schedulesByUserId(@PathVariable(value = "id") UUID id) throws JsonProcessingException {
        return scheduleService.schedulesByUserId(id);
    }

    @GetMapping(value = "/all/{id}/{semester}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> schedulesByCourseIdAndSemester(@PathVariable(value = "id") UUID id,
                                                                @PathVariable(value = "semester") ESubjectSemester semester) throws JsonProcessingException {
        return scheduleService.schedulesByCourseIdAndSemester(id, semester);
    }

    @GetMapping(value = "/details/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> scheduleById(@PathVariable(value = "id") UUID id) throws JsonProcessingException {
        return scheduleService.scheduleById(id);
    }

    @PostMapping(value = "/create/unique", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createUniqueSchedule(@Valid @RequestBody Schedule schedule) {
        return scheduleService.createUniqueSchedule(schedule);
    }

    @PostMapping(value = "/create/recurrent", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createRecurrentSchedule(@Valid @RequestBody List<Schedule> schedules) {
        return scheduleService.createRecurrentSchedule(schedules);
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateSchedule(@PathVariable(value = "id") UUID id, @Valid @RequestBody Schedule schedule) {
        return scheduleService.updateSchedule(schedule, id);
    }
}
