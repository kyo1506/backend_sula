package com.fzs.sula.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;

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
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private Ambiente ambiente;
    private Boolean concluido = false;
    @Column(nullable = false)
    private Timestamp dtInicio;
    @Column(nullable = false)
    private Timestamp dtFim;
    private Timestamp createdOn = Timestamp.from(Instant.now());
    private Timestamp updatedOn;
}