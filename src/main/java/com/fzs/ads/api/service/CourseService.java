package com.fzs.ads.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fzs.ads.api.model.Course;
import com.fzs.ads.api.repository.CourseRepository;
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
public class CourseService {
    private CourseRepository courseRepository;
    private final ObjectMapper objectMapper;

    public ResponseEntity<Object> courses() throws JsonProcessingException {
        var coursesList = courseRepository.findAll();
        if (!coursesList.isEmpty()) {
            return new ResponseEntity<>(objectMapper.writeValueAsString(coursesList), HttpStatus.OK);
        } else return new ResponseEntity<>("There are no courses registered", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> courseById(UUID id) throws JsonProcessingException {
        var course = courseRepository.findById(id);
        if (course.isPresent()) {
            return new ResponseEntity<>(objectMapper.writeValueAsString(course), HttpStatus.OK);
        } else return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> createCourse(Course model) {
        if (!courseRepository.existsCourseByName(model.getName())) {
            var course = new Course();
            course.setName(model.getName());
            course.setSubjects(model.getSubjects());
            Course courseSalvo = courseRepository.save(course);
            if (courseRepository.findById(courseSalvo.getId()).isPresent())
                return new ResponseEntity<>("Course registered successfully!", HttpStatus.OK);
            else
                return new ResponseEntity<>("An error occurred during processing", HttpStatus.INTERNAL_SERVER_ERROR);
        } else return new ResponseEntity<>("There is already a course with that name", HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public ResponseEntity<Object> updateCourse(Course model, UUID id) {
        if (courseRepository.findById(id).isPresent()) {
            Course course = courseRepository.findById(id).get();
            course.setName(model.getName());
            course.setIsActive(model.getIsActive());
            course.setSubjects(model.getSubjects());
            course.setUpdatedOn(Timestamp.from(Instant.now()));
            Course courseSalvo = courseRepository.save(course);
            if (courseRepository.findById(courseSalvo.getId()).isPresent())
                return new ResponseEntity<>("Course updated successfully", HttpStatus.OK);
            else
                return new ResponseEntity<>("An error occurred during processing", HttpStatus.INTERNAL_SERVER_ERROR);
        } else return new ResponseEntity<>("Course not found!", HttpStatus.NOT_FOUND);
    }
}
