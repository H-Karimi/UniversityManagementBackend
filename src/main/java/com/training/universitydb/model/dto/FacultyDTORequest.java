package com.training.universitydb.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.training.universitydb.model.ProfessorEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FacultyDTORequest {
    private String title;
    private Long headProfessor;
}
