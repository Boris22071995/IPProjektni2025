package com.etfbl.ip.BackendApp.service;

import com.etfbl.ip.BackendApp.model.requests.UserRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    public ResponseEntity<?> login(UserRequest userRequest);

    public String hashPassword(String password);
}
