package com.etfbl.ip.BackendApp.model.requests;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class InternshipRequest {

    private String title;

    private String description;

    private String requirements;

    private LocalDate startDate;

    private LocalDate endDate;

    private String restrictions;

    private String companyName;

    private List<String> technologies;
}
