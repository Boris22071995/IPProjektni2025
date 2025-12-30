package net.etfbl.ip.backendApp.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.etfbl.ip.backendApp.model.Company;
import net.etfbl.ip.backendApp.repository.CompanyRepository;
import net.etfbl.ip.backendApp.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public Company save(Company company) {
        company.setApproved(false);
        return companyRepository.save(company);
    }

    @Override
    public Company approved(Long id) {
        Company company = findById(id);
        company.setApproved(true);
        return companyRepository.save(company);
    }

    @Override
    public Company update(Long id, Company company) {
        Company existing = findById(id);
        existing.setName(company.getName());
        existing.setDescription(company.getDescription());
        return companyRepository.save(existing);
    }

    @Override
    public List<Company> findApproved() {
        return companyRepository.findAll().stream().filter(Company::getApproved).toList();
    }

    @Override
    public Company findById(Long id) {
        return companyRepository.findById(id).orElseThrow(() -> new RuntimeException("Company not found"));
    }
}
