package com.fzs.sula.api.repository;

import com.fzs.sula.api.enums.Semestre;
import com.fzs.sula.api.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    List<Agendamento> findAllByUser_Id(Long id);

    List<Agendamento> findAllByCurso_IdAndMateria_Semestre(Long id, Semestre semestre);

    @Query("select a from Agendamento a where a.ambiente.id = :id and " +
            "a.data = :date and a.horarioInicio >= :horarioInicio and a.horarioFim <= :horarioFim")
    Optional<Agendamento> findAgendamentoByAmbiente_IdAndDataAndHorarioInicioAndHorarioFim(
            @Param("id") Long id,
            @Param("date") Date date,
            @Param("horarioInicio") Time horarioInicio,
            @Param("horarioFim") Time horarioFim);
}