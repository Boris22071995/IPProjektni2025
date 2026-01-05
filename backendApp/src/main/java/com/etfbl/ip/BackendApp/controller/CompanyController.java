package com.etfbl.ip.BackendApp.controller;

import com.etfbl.ip.BackendApp.model.Company;
import com.etfbl.ip.BackendApp.model.requests.CompanyRequest;
import com.etfbl.ip.BackendApp.model.requests.CreateCompanyRequest;
import com.etfbl.ip.BackendApp.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<Void> createCompany(@RequestBody CreateCompanyRequest request){
        companyService.createCompany(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<Company> getAll(){
        return companyService.getAll();
    }

    @PutMapping("/{id}/activate")
    public ResponseEntity<Void> activate(@PathVariable Integer id) {
        companyService.activate(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Integer id) {
        companyService.deactivate(id);
        return ResponseEntity.ok().build();
    }
}
