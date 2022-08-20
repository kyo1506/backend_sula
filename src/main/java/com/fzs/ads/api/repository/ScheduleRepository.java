package com.fzs.ads.api.repository;

import com.fzs.ads.api.enums.ESubjectSemester;
import com.fzs.ads.api.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {
    List<Schedule> findAllByUser_Id(UUID id);

    List<Schedule> findAllByCourse_IdAndAndSubject_Semester(UUID id, ESubjectSemester semester);

    @Query("select a from Schedule a " +
            "where a.ambient.id = :id " +
            "and a.startDate <= :endDate and a.endDate >= :startDate " +
            "and a.isActive = true")
    Optional<Schedule> findScheduleByAmbient_IdAndStartDateAndEndDate(
            @Param("id") UUID id,
            @Param("startDate") Timestamp startDate,
            @Param("endDate") Timestamp endDate);
}