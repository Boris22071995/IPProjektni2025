package com.etfbl.ip.BackendApp.repository;

import com.etfbl.ip.BackendApp.model.Internship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InternshipRepository extends JpaRepository<Internship, Integer> {
    @Query("""
    SELECT i FROM Internship i
    JOIN FETCH i.company c
    LEFT JOIN FETCH i.technologies t
    WHERE c.approved = true
""")
    List<Internship> findAllApproved();

}
