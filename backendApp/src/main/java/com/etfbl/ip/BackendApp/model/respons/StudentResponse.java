package com.etfbl.ip.BackendApp.model.respons;

import lombok.Data;

@Data
public class StudentResponse {

    Integer id;
    String firstName;
    String lastName;
    String indexNumber;
    Integer yearOfStudy;

    String username;
    String password;
}
