package com.fzs.sula.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Curso implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String nome;
    @OneToMany(
            targetEntity = Materia.class,
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<Materia> materias;
    private Boolean ativo = true;
    private Timestamp createdOn = Timestamp.from(Instant.now());
    private Timestamp updatedOn;
}
