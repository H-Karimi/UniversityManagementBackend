package com.training.universitydb.repository;

import com.training.universitydb.model.TeachingCourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeachingCourseRepository extends JpaRepository<TeachingCourseEntity, Long> {
}
