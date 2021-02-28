package com.training.universitydb.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class SemesterDTOResponse {
    private Long id;
    private Timestamp startDate;
    private Timestamp endDate;
}
