package com.etfbl.ip.BackendApp.service;

import com.etfbl.ip.BackendApp.model.Company;
import com.etfbl.ip.BackendApp.model.requests.CompanyRequest;

import java.util.List;

public interface CompanyService {

    public Company save(CompanyRequest companyRequest);

    public List<Company> getAll();

    public Company findByName(String name);
}
