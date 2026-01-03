package com.etfbl.ip.BackendApp.model.requests;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CvEducationRequest {
    private String nameOfInstitution;
    private String titleName;
    private LocalDate startDate;
    private LocalDate endDate;
}
