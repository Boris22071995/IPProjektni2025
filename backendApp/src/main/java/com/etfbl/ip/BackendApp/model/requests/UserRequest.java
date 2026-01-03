package com.etfbl.ip.BackendApp.model.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRequest {

    @NotNull
    String username;

    @NotNull
    String password;

    @NotNull
    String role;
}
