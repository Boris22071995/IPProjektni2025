package net.etfbl.ip.backendApp.repository;

import net.etfbl.ip.backendApp.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
