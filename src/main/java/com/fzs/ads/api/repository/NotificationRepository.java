package com.fzs.ads.api.repository;

import com.fzs.ads.api.enums.ENotificationStatus;
import com.fzs.ads.api.model.Notification;
import com.fzs.ads.api.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {
    @Query("select a from Notification a " +
            "where a.status = :status ")
    List<Notification> findAllByStatus(
            @Param("status") ENotificationStatus status);
    boolean existsByScheduleAndStatus(Schedule schedule, ENotificationStatus status);
}