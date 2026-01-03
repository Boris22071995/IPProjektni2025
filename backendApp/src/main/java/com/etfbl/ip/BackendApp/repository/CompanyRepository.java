package com.etfbl.ip.BackendApp.repository;

import com.etfbl.ip.BackendApp.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    public Company findByName(String name);
}
