package com.fzs.sula.api.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Agendamento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(
            targetEntity = User.class,
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private User user;
    @ManyToOne(
            targetEntity = Ambiente.class,
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private Ambiente ambiente;
    @ManyToOne(
            targetEntity = Curso.class,
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private Curso curso;
    @ManyToOne(
            targetEntity = Materia.class,
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private Materia materia;
    @Column(nullable = false)
    private LocalDate data;
    @Column(nullable = false)
    private LocalTime horarioInicio;
    @Column(nullable = false)
    private LocalTime horarioFim;
    private LocalDateTime createdOn = LocalDateTime.now();
    private LocalDateTime updatedOn;
}