package com.training.universitydb.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.training.universitydb.model.CourseEntity;
import com.training.universitydb.model.ProfessorEntity;
import com.training.universitydb.model.SemesterEntity;
import com.training.universitydb.model.TakingCourseEntity;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class TeachingCourseDTOResponse {
    private Long id;
    private Timestamp semesterStartDate;
    private Timestamp semesterEndDate;
    private String course;
    private String professor;
    @JsonIgnore
    private TakingCourseEntity takingCourseEntity;
}
