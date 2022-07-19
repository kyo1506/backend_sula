package com.fzs.sula.api.model;

import com.fzs.sula.api.enums.Semestre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Materia implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String nome;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Semestre semestre;
    private Boolean ativo = true;
    private Timestamp createdOn = Timestamp.from(Instant.now());
    private Timestamp updatedOn;
}