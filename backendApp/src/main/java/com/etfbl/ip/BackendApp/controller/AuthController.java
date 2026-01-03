package com.etfbl.ip.BackendApp.controller;

import com.etfbl.ip.BackendApp.model.requests.UserRequest;
import com.etfbl.ip.BackendApp.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest request) {
        return authService.login(request);
    }
}
