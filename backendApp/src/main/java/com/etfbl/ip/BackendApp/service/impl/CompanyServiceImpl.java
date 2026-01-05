package com.etfbl.ip.BackendApp.service.impl;

import com.etfbl.ip.BackendApp.model.Company;
import com.etfbl.ip.BackendApp.model.Role;
import com.etfbl.ip.BackendApp.model.User;
import com.etfbl.ip.BackendApp.model.requests.CompanyRequest;
import com.etfbl.ip.BackendApp.model.requests.CreateCompanyRequest;
import com.etfbl.ip.BackendApp.repository.CompanyRepository;
import com.etfbl.ip.BackendApp.repository.RoleRepository;
import com.etfbl.ip.BackendApp.repository.UserRepository;
import com.etfbl.ip.BackendApp.service.CompanyService;
import com.etfbl.ip.BackendApp.util.PasswordUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

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

    @Transactional
    @Override
    public void createCompany(CreateCompanyRequest companyRequest) {
        User user = new User();
        Role role = roleRepository.findByName("COMPANY");
        role.setName("COMPANY");
        user.setUsername(companyRequest.getUsername());
        user.setPassword(PasswordUtil.hashPassword(companyRequest.getPassword()));
        user.setRole(role);

        userRepository.save(user);

        Company company = new Company();
        company.setName(companyRequest.getCompanyName());
        company.setDescription(companyRequest.getCompanyDescription());
        company.setApproved(false);
        company.setUser(user);

        companyRepository.save(company);
    }

    @Override
    public void activate(Integer id) {
        Company company = companyRepository.findById(id)
                .orElseThrow();

        company.setApproved(true);
        companyRepository.save(company);
    }

    @Override
    public void deactivate(Integer id) {
        Company company = companyRepository.findById(id)
                .orElseThrow();

        company.setApproved(false);
        companyRepository.save(company);
    }
}
