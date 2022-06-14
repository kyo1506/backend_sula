package com.fzs.sula.api.repository;

import com.fzs.sula.api.model.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MateriaRepository extends JpaRepository<Materia, Long> {
    boolean existsMateriaByNome(String nome);
}