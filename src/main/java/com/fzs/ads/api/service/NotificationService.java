package com.fzs.ads.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fzs.ads.api.enums.ENotificationStatus;
import com.fzs.ads.api.model.Notification;
import com.fzs.ads.api.repository.NotificationRepository;
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
public class NotificationService {
    private final ObjectMapper objectMapper;
    private NotificationRepository notificationRepository;

    private ScheduleService scheduleService;

    public ResponseEntity<Object> notifications() throws JsonProcessingException {
        var notificationsList = notificationRepository.findAll();
        if (!notificationsList.isEmpty()) {
            return new ResponseEntity<>(objectMapper.writeValueAsString(notificationsList), HttpStatus.OK);
        } else return new ResponseEntity<>("There are no notifications registered", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> notificationsByStatus(ENotificationStatus status) throws JsonProcessingException {
        var notificationsList = notificationRepository.findAllByStatus(status);
        if (!notificationsList.isEmpty()) {
            return new ResponseEntity<>(objectMapper.writeValueAsString(notificationsList), HttpStatus.OK);
        } else return new ResponseEntity<>("Notifications not found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> notificationById(UUID id) throws JsonProcessingException {
        var notification = notificationRepository.findById(id);
        if (notification.isPresent()) {
            return new ResponseEntity<>(objectMapper.writeValueAsString(notification), HttpStatus.OK);
        } else return new ResponseEntity<>("Notification not found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> createNotification(Notification model) {
        if (!notificationRepository.existsByScheduleAndStatus(model.getSchedule(), model.getStatus())) {
            var notification = new Notification();
            notification.setSchedule(model.getSchedule());
            notification.setStatus(model.getStatus());
            Notification notificationSaved = notificationRepository.save(notification);
            if (notificationRepository.findById(notificationSaved.getId()).isPresent())
                return new ResponseEntity<>("Notification inserted successfuly", HttpStatus.OK);
            else
                return new ResponseEntity<>("An error occurred during processing", HttpStatus.INTERNAL_SERVER_ERROR);
        } else return new ResponseEntity<>("There is already an active notification for this schedule", HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public ResponseEntity<Object> updateNotification(Notification model, UUID id) {
        if (notificationRepository.findById(id).isPresent()) {
            var notification = notificationRepository.findById(id).get();
            notification.setSchedule(model.getSchedule());
            notification.setStatus(model.getStatus());
            notification.setUpdatedOn(Timestamp.from(Instant.now()));

            if (notification.getStatus().equals(ENotificationStatus.APPROVED))
                scheduleService.updateSchedule(model.getSchedule(), model.getSchedule().getId());

            Notification notificationSaved = notificationRepository.save(notification);

            if (notificationRepository.findById(notificationSaved.getId()).isPresent())
                return new ResponseEntity<>("Notification updated successfuly", HttpStatus.OK);
            else
                return new ResponseEntity<>("An error occurred during processing", HttpStatus.INTERNAL_SERVER_ERROR);
        } else return new ResponseEntity<>("Notification not found for this identification", HttpStatus.NOT_FOUND);
    }
}
