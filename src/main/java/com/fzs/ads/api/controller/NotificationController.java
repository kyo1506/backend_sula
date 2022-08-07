package com.fzs.ads.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fzs.ads.api.enums.ENotificationStatus;
import com.fzs.ads.api.model.Notification;
import com.fzs.ads.api.service.NotificationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/notification")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@SecurityRequirement(name = "bearerAuth")
public class NotificationController {
    private NotificationService notificationService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> notifications() throws JsonProcessingException {
        return notificationService.notifications();
    }

    @GetMapping(value = "/all/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> notificationsByStatus(@PathVariable(value = "status") ENotificationStatus status) throws JsonProcessingException {
        return notificationService.notificationsByStatus(status);
    }

    @GetMapping(value = "/details/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> notificationById(@PathVariable(value = "id") UUID id) throws JsonProcessingException {
        return notificationService.notificationById(id);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createNotification(@Valid @RequestBody Notification notification) {
        return notificationService.createNotification(notification);
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateNotification(@PathVariable(value = "id") UUID id, @Valid @RequestBody Notification notification) {
        return notificationService.updateNotification(notification, id);
    }
}
