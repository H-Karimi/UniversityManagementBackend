package com.training.universitydb.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.training.universitydb.model.ProfessorEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FacultyDTOResponse {
    private Long id;
    private String title;
    private String headProfessor;
}
