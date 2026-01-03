package com.etfbl.ip.BackendApp.repository;

import com.etfbl.ip.BackendApp.model.Internship;
import com.etfbl.ip.BackendApp.model.InternshipApplication;
import com.etfbl.ip.BackendApp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InternshipApplicationRepository extends JpaRepository<InternshipApplication, Integer> {
    boolean existsByStudentAndInternship(Student student, Optional<Internship> internship);
}
