package com.etfbl.ip.BackendApp.model.requests;

import lombok.Data;

@Data
public class CompanyRequest {

    String name;
    String description;
    Boolean approved;

    String username;
}
