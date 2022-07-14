package com.fzs.sula.api.repository;

import com.fzs.sula.api.enums.Semestre;
import com.fzs.sula.api.model.Agendamento;
import com.fzs.sula.api.model.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    List<Agendamento> findAllByUser_Id(Long id);

    @Query("SELECT e FROM Agendamento e " +
            "WHERE e.curso.id= (:id) and e.materia.semestre= (:semestre)")
    List<Agendamento> agendamentosCursoSemestre(@Param("id") Long id, @Param("semestre") Semestre semestre);

    boolean existsAgendamentoByAmbiente_IdAndDataAndHorarioInicioAndHorarioFim(Long id,
                                                                               LocalDate data,
                                                                               LocalTime horarioInicio,
                                                                               LocalTime horarioFim);
}