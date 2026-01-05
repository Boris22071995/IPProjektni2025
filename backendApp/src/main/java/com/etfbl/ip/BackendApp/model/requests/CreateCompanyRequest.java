package com.etfbl.ip.BackendApp.model.requests;

import lombok.Data;

@Data
public class CreateCompanyRequest {

    private String companyName;
    private String companyDescription;

    private String username;
    private String password;
}
