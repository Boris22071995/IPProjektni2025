package com.etfbl.ip.BackendApp.service.impl;

import com.etfbl.ip.BackendApp.model.Student;
import com.etfbl.ip.BackendApp.model.User;
import com.etfbl.ip.BackendApp.model.requests.UserRequest;
import com.etfbl.ip.BackendApp.repository.RoleRepository;
import com.etfbl.ip.BackendApp.repository.StudentRepository;
import com.etfbl.ip.BackendApp.repository.UserRepository;
import com.etfbl.ip.BackendApp.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final RoleRepository roleRepository;

    @Override
    public ResponseEntity<?> login(UserRequest userRequest) {
        User user = userRepository.findByUsername(userRequest.getUsername());
        if(!user.getRole().getName().equals(userRequest.getRole())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid role");
        }

        String hashedPassword = hashPassword(userRequest.getPassword());
        if(!user.getPassword().equals(hashedPassword)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        Student student = studentRepository.findByUserId(user.getId());
        return ResponseEntity.ok(student);
    }

    @Override
    public String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException("Password hashing failed");
        }
    }
}
