package com.training.universitydb.repository;

import com.training.universitydb.model.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
    public Optional<CourseEntity> findByTitle(String title);
}
