package com.etfbl.ip.BackendApp.controller;

import com.etfbl.ip.BackendApp.model.Company;
import com.etfbl.ip.BackendApp.model.requests.CompanyRequest;
import com.etfbl.ip.BackendApp.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public Company create(@RequestBody CompanyRequest companyRequest) {
        return companyService.save(companyRequest);
    }

    @GetMapping
    public List<Company> getAll(){
        return companyService.getAll();
    }
}
