package com.etfbl.ip.BackendApp.service;

import com.etfbl.ip.BackendApp.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    Role save(Role role);

    Optional<Role> findById(Integer id);

    List<Role> findAll();

    Role findByName(String name);
}
