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

    @Query("SELECT e FROM ManutencaoAmbiente e " +
            "WHERE e.ambiente.id= (:id) and e.dtInicio= (:dtInicio) and e.dtFim= (:dtFim) and e.concluido = true")
    Optional<ManutencaoAmbiente> ManutencaoAmbienteExists(@Param("ambiente") Long id, @Param("dtInicio") LocalDateTime dtInicio, @Param("dtFim") LocalDateTime dtFim);
}