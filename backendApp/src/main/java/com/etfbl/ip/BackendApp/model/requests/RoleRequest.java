package com.etfbl.ip.BackendApp.model.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RoleRequest {

    @NotNull
    private String name;
}
