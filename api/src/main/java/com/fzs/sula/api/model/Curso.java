package com.fzs.sula.api.model;

import com.fzs.sula.api.enums.Materia;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "curso")
public class Curso implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;
    @ElementCollection(targetClass = Materia.class)
    @CollectionTable
    @Enumerated(EnumType.STRING)
    private Collection<Materia> materias;
    private Boolean ativo = true;
    private LocalDateTime createdOn = LocalDateTime.now();
    private LocalDateTime updatedOn;
}
