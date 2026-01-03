package com.etfbl.ip.BackendApp.controller;

import com.etfbl.ip.BackendApp.model.requests.CvRequest;
import com.etfbl.ip.BackendApp.service.CvService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/cv")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CvController {
    private final CvService cvService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createCv(@RequestPart("cv") CvRequest request, @RequestPart(value = "image", required = false) MultipartFile image) {
        cvService.createCv(request, image);
        return ResponseEntity.ok().build();
    }
}
