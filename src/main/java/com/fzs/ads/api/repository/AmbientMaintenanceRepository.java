package com.fzs.ads.api.repository;

import com.fzs.ads.api.model.AmbientMaintenance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AmbientMaintenanceRepository extends JpaRepository<AmbientMaintenance, UUID> {
    List<AmbientMaintenance> findAllByAmbient_Id(UUID id);

    Optional<AmbientMaintenance> findAmbientMaintenanceByAmbient_IdAndStartDateAndEndDateAndIsDone(UUID id, Timestamp startDate, Timestamp endDate, Boolean isDone);
}