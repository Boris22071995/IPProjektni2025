package com.etfbl.ip.BackendApp.controller;

import com.etfbl.ip.BackendApp.model.Role;
import com.etfbl.ip.BackendApp.model.requests.RoleRequest;
import com.etfbl.ip.BackendApp.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public Role create(@RequestBody RoleRequest request){
        Role role = new Role();
        role.setName(request.getName());
        return roleService.save(role);
    }
}
