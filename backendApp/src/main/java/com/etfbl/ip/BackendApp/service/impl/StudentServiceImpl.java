package com.etfbl.ip.BackendApp.service.impl;

import com.etfbl.ip.BackendApp.model.Student;
import com.etfbl.ip.BackendApp.repository.StudentRepository;
import com.etfbl.ip.BackendApp.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student update(Integer id, Student student) {
        Student existing = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        existing.setIndexNumber(student.getIndexNumber());
        existing.setFirstName(student.getFirstName());
        existing.setLastName(student.getLastName());
        existing.setYearOfStudy(student.getYearOfStudy());
        existing.setUser(student.getUser());

        return studentRepository.save(existing);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(Integer id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @Override
    public Student findByUserId(Integer id) {
        return studentRepository.findByUserId(id);
    }

    @Override
    public Student findByIndexNumber(String indexNumber) {
        return studentRepository.findByIndexNumber(indexNumber);
    }

    @Override
    public void delete(Integer id) {
        studentRepository.deleteById(id);
    }
}
