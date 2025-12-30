package net.etfbl.ip.backendApp.repository;

import net.etfbl.ip.backendApp.model.Intership;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IntershipRepository extends JpaRepository<Intership, Long> {
    List<Intership> findByCompanyId(Long companyId);
}
