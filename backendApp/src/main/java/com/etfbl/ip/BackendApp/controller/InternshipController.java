package com.etfbl.ip.BackendApp.controller;

import com.etfbl.ip.BackendApp.model.Internship;
import com.etfbl.ip.BackendApp.model.requests.InternshipRequest;
import com.etfbl.ip.BackendApp.model.respons.InternshipResponse;
import com.etfbl.ip.BackendApp.service.InternshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/internships")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class InternshipController {

    private final InternshipService internshipService;

    @PostMapping
    public Internship create(@RequestBody InternshipRequest internshipRequest) {
        return internshipService.save(internshipRequest);
    }

    @GetMapping
    public ResponseEntity<List<InternshipResponse>> getAll() {
        return ResponseEntity.ok(
                internshipService.getAllAvailableInternships()
        );
    }
}
