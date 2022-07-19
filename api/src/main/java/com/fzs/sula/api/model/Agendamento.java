package com.fzs.sula.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;

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
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private User user;
    @ManyToOne(
            targetEntity = Ambiente.class,
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private Ambiente ambiente;
    @ManyToOne(
            targetEntity = Curso.class,
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private Curso curso;
    @ManyToOne(
            targetEntity = Materia.class,
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private Materia materia;
    @Column(nullable = false)
    private Date data;
    @Column(nullable = false)
    private Time horarioInicio;
    @Column(nullable = false)
    private Time horarioFim;
    private Timestamp createdOn = Timestamp.from(Instant.now());
    private Timestamp updatedOn;
}