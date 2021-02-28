package com.training.universitydb.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class SemesterDTORequest {
    private Timestamp startDate;
    private Timestamp endDate;
}
