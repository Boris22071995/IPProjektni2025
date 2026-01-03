package com.etfbl.ip.BackendApp.repository;

import com.etfbl.ip.BackendApp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    public Student findByUserId(Integer id);

    public Student findByIndexNumber(String indexNumber);
}
