package com.etfbl.ip.BackendApp.service;

import com.etfbl.ip.BackendApp.model.Student;

import java.util.List;

public interface StudentService {

    Student save(Student student);

    Student update(Integer id, Student student);

    List<Student> findAll();

    Student findById(Integer id);

    Student findByUserId(Integer id);

    Student findByIndexNumber(String indexNumber);

    void delete(Integer id);
}
