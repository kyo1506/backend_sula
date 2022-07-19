package com.fzs.sula.api.model;

import com.fzs.sula.api.enums.Caracteristica;
import com.fzs.sula.api.enums.Disponibilidade;
import com.fzs.sula.api.enums.TipoAmbiente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ambiente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String nome;
    @Enumerated(EnumType.STRING)
    private TipoAmbiente tipoAmbiente;
    @ElementCollection(targetClass = Disponibilidade.class)
    @CollectionTable
    @Enumerated(EnumType.STRING)
    Collection<Disponibilidade> disponibilidades;
    @ElementCollection(targetClass = Caracteristica.class)
    @CollectionTable
    @Enumerated(EnumType.STRING)
    Collection<Caracteristica> caracteristicas;
    @Column(length = 100)
    private String referencia;
    private Long numero;
    private Boolean ativo = true;
    private Timestamp createdOn = Timestamp.from(Instant.now());
    private Timestamp updatedOn;
}