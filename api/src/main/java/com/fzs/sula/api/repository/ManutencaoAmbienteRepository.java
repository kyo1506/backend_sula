package com.fzs.sula.api.repository;

import com.fzs.sula.api.model.ManutencaoAmbiente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ManutencaoAmbienteRepository extends JpaRepository<ManutencaoAmbiente, Long> {
    List<ManutencaoAmbiente> findAllByAmbiente_Id(Long id);

    @Query("SELECT u FROM ManutencaoAmbiente u WHERE u.ambiente.id = ?1 " +
            "and u.dtInicio = ?2 " +
            "and u.dtFim = ?3 " +
            "and u.concluido = true")
    ManutencaoAmbiente existsManutencao(Long id, LocalDateTime dtInicio, LocalDateTime dtFim);
}