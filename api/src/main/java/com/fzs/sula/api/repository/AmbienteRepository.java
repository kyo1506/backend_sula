package com.fzs.sula.api.repository;

import com.fzs.sula.api.model.Ambiente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmbienteRepository extends JpaRepository<Ambiente, Long> {
    boolean existsAmbienteByNome(String nome);
}