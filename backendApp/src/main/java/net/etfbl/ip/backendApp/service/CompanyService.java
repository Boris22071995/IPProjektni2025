package net.etfbl.ip.backendApp.service;

import net.etfbl.ip.backendApp.model.Company;

import java.util.List;

public interface CompanyService {

    Company save(Company company);

    Company approved(Long id);

    Company update(Long id, Company company);

    List<Company> findApproved();

    Company findById(Long id);
}
