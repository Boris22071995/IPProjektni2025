package com.etfbl.ip.BackendApp.service.impl;

import com.etfbl.ip.BackendApp.model.Company;
import com.etfbl.ip.BackendApp.model.User;
import com.etfbl.ip.BackendApp.model.requests.CompanyRequest;
import com.etfbl.ip.BackendApp.repository.CompanyRepository;
import com.etfbl.ip.BackendApp.repository.UserRepository;
import com.etfbl.ip.BackendApp.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    @Override
    public Company save(CompanyRequest companyRequest) {

        User user = userRepository.findByUsername(companyRequest.getUsername());

        Company company = new Company();
        company.setUser(user);
        company.setApproved(true);
        company.setDescription(companyRequest.getDescription());
        company.setName(companyRequest.getName());
        return companyRepository.save(company);
    }

    @Override
    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    @Override
    public Company findByName(String name) {
        return companyRepository.findByName(name);
    }
}
