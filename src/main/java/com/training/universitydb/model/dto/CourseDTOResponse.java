package com.training.universitydb.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDTOResponse {
    private Long id;
    private String title;
    private int credit;
}