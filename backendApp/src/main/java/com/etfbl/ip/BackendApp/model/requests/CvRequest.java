package com.etfbl.ip.BackendApp.model.requests;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CvRequest {
    private StudentRequest student;
    private CvInfoRequest cv;

    private List<CvEducationRequest> educations;
    private List<CvExperienceRequest> experiences;
    private List<CvSkillRequest> skills;
    private List<CvInterestRequest> interests;
}
