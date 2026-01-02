package com.etfbl.ip.BackendApp.service.impl;

import com.etfbl.ip.BackendApp.model.Role;
import com.etfbl.ip.BackendApp.repository.RoleRepository;
import com.etfbl.ip.BackendApp.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Optional<Role> findById(Integer id) {
        Optional<Role> role = roleRepository.findById(id);
        return role;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
