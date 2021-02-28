package com.training.universitydb.repository;

import com.training.universitydb.model.TakingCourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TakingCourseRepository extends JpaRepository<TakingCourseEntity, Long> {
}
