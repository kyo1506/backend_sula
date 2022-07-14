package com.fzs.sula.api.model;

import com.fzs.sula.api.enums.Semestre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
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
    private LocalDateTime createdOn = LocalDateTime.now();
    private LocalDateTime updatedOn;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Materia materia = (Materia) o;
        return id != null && Objects.equals(id, materia.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}