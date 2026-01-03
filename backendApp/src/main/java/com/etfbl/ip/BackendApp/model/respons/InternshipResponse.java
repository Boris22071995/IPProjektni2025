package com.etfbl.ip.BackendApp.model.respons;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class InternshipResponse {
    private Integer id;
    private String title;
    private String description;
    private String requirements;
    private LocalDate startDate;
    private LocalDate endDate;
    private String restriction;

    private String companyName;
    private List<String> technologies;
}
