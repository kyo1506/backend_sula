package com.fzs.ads.api.repository;

import com.fzs.ads.api.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID> {
    boolean existsCourseByName(String name);
}