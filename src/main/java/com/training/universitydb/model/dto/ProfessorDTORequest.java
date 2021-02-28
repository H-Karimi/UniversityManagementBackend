package com.training.universitydb.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.training.universitydb.model.FacultyEntity;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ProfessorDTORequest {
    private String name;
    private Timestamp birthDate;
    private Long faculty;
}
