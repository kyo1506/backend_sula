package com.fzs.sula.api.repository;

import com.fzs.sula.api.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    boolean existsCursoByNome(String nome);
}