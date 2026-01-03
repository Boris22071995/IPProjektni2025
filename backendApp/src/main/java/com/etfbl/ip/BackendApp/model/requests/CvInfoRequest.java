package com.etfbl.ip.BackendApp.model.requests;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CvInfoRequest {
    private String email;
    private String phone;
    private String address;
    private String city;
    private Integer postCode;
    private LocalDate dateOfBirth;
    private String imageFilePath;
}
