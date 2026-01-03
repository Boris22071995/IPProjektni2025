package com.etfbl.ip.BackendApp.model.requests;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CvExperienceRequest {
    private String company;
    private String position;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
}
