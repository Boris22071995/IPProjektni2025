package net.etfbl.ip.backendApp.controller;

import lombok.RequiredArgsConstructor;
import net.etfbl.ip.backendApp.model.Company;
import net.etfbl.ip.backendApp.service.CompanyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public Company create(@RequestBody Company company){
        return companyService.save(company);
    }

    @PutMapping("/{id}")
    public Company update(@PathVariable Long id, @RequestBody Company company) {
        return companyService.update(id, company);
    }

    @PutMapping("/{id}/approve")
    public Company approve(@PathVariable Long id) {
        return companyService.approved(id);
    }

    @GetMapping("/approved")
    public List<Company> getApproved() {
        return companyService.findApproved();
    }

    @GetMapping("/{id}")
    public Company getById(@PathVariable Long id) {
        return companyService.findById(id);
    }
}
