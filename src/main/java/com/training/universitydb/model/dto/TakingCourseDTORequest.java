package com.training.universitydb.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.training.universitydb.model.TeachingCourseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TakingCourseDTORequest {
    private Long teachingCourseEntity;
}
