package com.fzs.sula.api.model;

import com.fzs.sula.api.enums.Caracteristica;
import com.fzs.sula.api.enums.Disponibilidade;
import com.fzs.sula.api.enums.TipoAmbiente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@Entity
@Getter
@Setter
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
    private LocalDateTime createdOn = LocalDateTime.now();
    private LocalDateTime updatedOn;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Ambiente ambiente = (Ambiente) o;
        return id != null && Objects.equals(id, ambiente.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}