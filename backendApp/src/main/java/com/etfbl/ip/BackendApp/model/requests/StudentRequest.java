package com.etfbl.ip.BackendApp.model.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StudentRequest {

    @NotNull
    String firstName;

    @NotNull
    String lastName;

    @NotNull
    String indexNumber;

    @NotNull
    Integer yearOfStudy;

    @NotNull
    String username;
}
