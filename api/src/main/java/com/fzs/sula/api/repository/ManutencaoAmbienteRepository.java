package com.fzs.sula.api.repository;

import com.fzs.sula.api.model.ManutencaoAmbiente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ManutencaoAmbienteRepository extends JpaRepository<ManutencaoAmbiente, Long> {
    List<ManutencaoAmbiente> findAllByAmbiente_Id(Long id);
    Optional<ManutencaoAmbiente> findManutencaoAmbienteByAmbiente_IdAndDtInicioAndDtFim(Long id, Timestamp dtInicio, Timestamp dtFim);
}