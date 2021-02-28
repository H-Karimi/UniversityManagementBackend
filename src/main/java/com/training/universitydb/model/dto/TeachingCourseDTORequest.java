package com.training.universitydb.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeachingCourseDTORequest {
    private Long semester;
    private Long course;
    private Long professor;
    private Long takingCourse;
}
