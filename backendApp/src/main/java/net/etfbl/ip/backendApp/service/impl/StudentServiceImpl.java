package net.etfbl.ip.backendApp.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.etfbl.ip.backendApp.model.Student;
import net.etfbl.ip.backendApp.repository.StudentRepository;
import net.etfbl.ip.backendApp.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public Student save(Student student) {
        log.info("Saving student: " + student.getIndexNumber());
        return studentRepository.save(student);
    }

    @Override
    public Student update(Long id, Student student) {
        log.info("Updating student with id: " + id);

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
    public Student findById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }
}
