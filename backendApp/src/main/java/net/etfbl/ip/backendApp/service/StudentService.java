package net.etfbl.ip.backendApp.service;

import net.etfbl.ip.backendApp.model.Student;

import java.util.List;

public interface StudentService {

    Student save(Student student);

    Student update(Long id, Student student);

    List<Student> findAll();

    Student findById(Long id);

    void delete(Long id);
}
