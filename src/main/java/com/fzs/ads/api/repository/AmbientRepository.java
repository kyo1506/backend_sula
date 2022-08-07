package com.fzs.ads.api.repository;

import com.fzs.ads.api.model.Ambient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AmbientRepository extends JpaRepository<Ambient, UUID> {
    boolean existsAmbientByName(String name);
}