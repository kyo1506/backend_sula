package com.fzs.ads.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fzs.ads.api.enums.ESubjectSemester;
import com.fzs.ads.api.model.Schedule;
import com.fzs.ads.api.repository.ScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ScheduleService {
    private final ObjectMapper objectMapper;
    private ScheduleRepository scheduleRepository;

    public ResponseEntity<Object> schedules() throws JsonProcessingException {
        var schedulesList = scheduleRepository.findAll();
        if (!schedulesList.isEmpty()) {
            return new ResponseEntity<>(objectMapper.writeValueAsString(schedulesList), HttpStatus.OK);
        } else return new ResponseEntity<>("There are no schedules registered", HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<Object> schedulesByCourseIdAndSemester(UUID id, ESubjectSemester semester) throws JsonProcessingException {
        var schedulesList = scheduleRepository.findAllByCourse_IdAndAndSubject_Semester(id, semester);
        if (!schedulesList.isEmpty()) {
            return new ResponseEntity<>(objectMapper.writeValueAsString(schedulesList), HttpStatus.OK);
        } else return new ResponseEntity<>("Notifications not found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> schedulesByUserId(UUID id) throws JsonProcessingException {
        var schedulesList = scheduleRepository.findAllByUser_Id(id);
        if (!schedulesList.isEmpty()) {
            return new ResponseEntity<>(objectMapper.writeValueAsString(schedulesList), HttpStatus.OK);
        } else return new ResponseEntity<>("Notifications not found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> scheduleById(UUID id) throws JsonProcessingException {
        var schedule = scheduleRepository.findById(id);
        if (schedule.isPresent()) {
            return new ResponseEntity<>(objectMapper.writeValueAsString(schedule), HttpStatus.OK);
        } else return new ResponseEntity<>("Schedule not found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> createUniqueSchedule(Schedule model) {
        var existsSchedule = scheduleRepository.findScheduleByAmbient_IdAndStartDateAndEndDate(
                model.getAmbient().getId(),
                model.getStartDate(),
                model.getEndDate());

        if (existsSchedule.isEmpty()) {
            var schedule = new Schedule();
            schedule.setAmbient(model.getAmbient());
            schedule.setCourse(model.getCourse());
            schedule.setSubject(model.getSubject());
            schedule.setUser(model.getUser());
            schedule.setStartDate(model.getStartDate());
            schedule.setEndDate(model.getEndDate());
            Schedule scheduleSaved = scheduleRepository.save(schedule);
            if (scheduleRepository.findById(scheduleSaved.getId()).isPresent())
                return new ResponseEntity<>("Schedule inserted successfuly", HttpStatus.OK);
            else
                return new ResponseEntity<>("An error occurred during processing", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(existsSchedule, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public ResponseEntity<Object> createRecurrentSchedule(List<Schedule> listModel) {
        List<Schedule> errorList = new ArrayList<>();

        for (Schedule rowSchedule : listModel) {
            Optional<Schedule> existsSchedule = scheduleRepository.findScheduleByAmbient_IdAndStartDateAndEndDate(
                    rowSchedule.getAmbient().getId(),
                    rowSchedule.getStartDate(),
                    rowSchedule.getEndDate());

            if (existsSchedule.isEmpty()) {
                var schedule = new Schedule();
                schedule.setAmbient(rowSchedule.getAmbient());
                schedule.setCourse(rowSchedule.getCourse());
                schedule.setSubject(rowSchedule.getSubject());
                schedule.setUser(rowSchedule.getUser());
                schedule.setStartDate(rowSchedule.getStartDate());
                schedule.setEndDate(rowSchedule.getEndDate());
                Schedule scheduleSalvo = scheduleRepository.save(schedule);
                if (scheduleRepository.findById(scheduleSalvo.getId()).isEmpty())
                    return new ResponseEntity<>("An error occurred during processing", HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                errorList.add(existsSchedule.get());
            }
        }

        if (errorList.isEmpty()) {
            return new ResponseEntity<>("Schedules registered successfuly", HttpStatus.OK);
        } else
            return new ResponseEntity<>(errorList, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public ResponseEntity<Object> updateSchedule(Schedule model, UUID id) {
        if (scheduleRepository.findById(id).isPresent()) {
            var schedule = scheduleRepository.findById(id).get();
            schedule.setAmbient(model.getAmbient());
            schedule.setCourse(model.getCourse());
            schedule.setSubject(model.getSubject());
            schedule.setUser(model.getUser());
            schedule.setStartDate(model.getStartDate());
            schedule.setEndDate(model.getEndDate());
            schedule.setIsActive(model.getIsActive());
            schedule.setUpdatedOn(Timestamp.from(Instant.now()));
            Schedule scheduleSalvo = scheduleRepository.save(schedule);
            if (scheduleRepository.findById(scheduleSalvo.getId()).isPresent())
                return new ResponseEntity<>("Schedule updated successfully", HttpStatus.OK);
            else
                return new ResponseEntity<>("An error occurred during processing", HttpStatus.INTERNAL_SERVER_ERROR);
        } else return new ResponseEntity<>("Schedule not found", HttpStatus.NOT_FOUND);
    }
}
