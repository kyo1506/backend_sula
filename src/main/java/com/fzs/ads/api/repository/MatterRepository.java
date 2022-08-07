package com.fzs.ads.api.repository;

import com.fzs.ads.api.model.Matter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MatterRepository extends JpaRepository<Matter, UUID> {
    boolean existsMatterByName(String name);
}