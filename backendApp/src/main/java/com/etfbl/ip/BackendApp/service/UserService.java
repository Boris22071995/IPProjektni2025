package com.etfbl.ip.BackendApp.service;

import com.etfbl.ip.BackendApp.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(User user);

    List<User> findAll();

    User findById(Integer id);

    User findByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);

}
