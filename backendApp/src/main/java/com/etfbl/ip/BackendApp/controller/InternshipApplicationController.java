package com.etfbl.ip.BackendApp.controller;

import com.etfbl.ip.BackendApp.model.requests.InternshipApplicationRequest;
import com.etfbl.ip.BackendApp.service.InternshipApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/internship-applications")
@RequiredArgsConstructor
@CrossOrigin
public class InternshipApplicationController {

    private final InternshipApplicationService service;

    @PostMapping
    public ResponseEntity<?> applay(@RequestBody InternshipApplicationRequest internshipApplicationRequest) {
        service.apply(internshipApplicationRequest);
        return ResponseEntity.ok().build();
    }
}
