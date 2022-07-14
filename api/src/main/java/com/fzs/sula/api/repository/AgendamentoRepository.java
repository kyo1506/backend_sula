package com.fzs.sula.api.repository;

import com.fzs.sula.api.enums.Semestre;
import com.fzs.sula.api.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    List<Agendamento> findAllByUser_Id(Long id);

    List<Agendamento> findAllByCurso_IdAndMateria_Semestre(Long id, Semestre semestre);

    boolean existsAgendamentoByAmbiente_IdAndDataAndHorarioInicioAndHorarioFim(Long id,
                                                                               LocalDate data,
                                                                               LocalTime horarioInicio,
                                                                               LocalTime horarioFim);
}