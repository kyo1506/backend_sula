package com.fzs.ads.api.repository;

import com.fzs.ads.api.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubjectRepository extends JpaRepository<Subject, UUID> {
    boolean existsSubjectByName(String name);
}