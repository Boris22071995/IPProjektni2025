package com.etfbl.ip.BackendApp.repository;

import com.etfbl.ip.BackendApp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByName(String name);
}
