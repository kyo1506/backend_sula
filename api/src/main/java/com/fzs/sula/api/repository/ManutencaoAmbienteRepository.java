package com.fzs.sula.api.repository;

import com.fzs.sula.api.model.ManutencaoAmbiente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ManutencaoAmbienteRepository extends JpaRepository<ManutencaoAmbiente, Long> {
    @Query("SELECT e FROM ManutencaoAmbiente e WHERE e.ambiente.id= (:id)")
    List<ManutencaoAmbiente> getManutencoesAmbiente(@Param("id") Long id);

    @Query("SELECT e.id FROM ManutencaoAmbiente e " +
            "WHERE e.ambiente.id= (:id) and e.dtInicio= (:dtInicio) and e.dtFim= (:dtFim) and e.concluido= true")
    Long ManutencaoAmbienteExists(@Param("id") Long id, @Param("dtInicio") LocalDateTime dtInicio, @Param("dtFim") LocalDateTime dtFim);
}