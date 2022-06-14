package com.fzs.sula.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ManutencaoAmbiente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(
            targetEntity = Ambiente.class,
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private Ambiente ambiente;
    private Boolean concluido = false;
    @Column(nullable = false)
    private LocalDateTime dtInicio;
    @Column(nullable = false)
    private LocalDateTime dtFim;
    private LocalDateTime createdOn = LocalDateTime.now();
    private LocalDateTime updatedOn;
}