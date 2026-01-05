package com.etfbl.ip.BackendApp.service;

import com.etfbl.ip.BackendApp.model.Student;
import com.etfbl.ip.BackendApp.model.requests.StudentRequest;
import com.etfbl.ip.BackendApp.model.respons.StudentResponse;

import java.util.List;

public interface StudentService {

    Student save(Student student);

    Student update(String index, StudentRequest studentRequest);

    List<Student> findAll();

    Student findById(Integer id);

    Student findByUserId(Integer id);

    Student findByIndexNumber(String indexNumber);

    List<StudentResponse> findAllForFaculty();

    void delete(String index);

    void createStudent(StudentRequest studentRequest);
}
