package net.etfbl.ip.backendApp.repository;

import net.etfbl.ip.backendApp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
