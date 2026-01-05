package com.etfbl.ip.BackendApp.repository;

import com.etfbl.ip.BackendApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);

    boolean existsByUsername(String username);


}
