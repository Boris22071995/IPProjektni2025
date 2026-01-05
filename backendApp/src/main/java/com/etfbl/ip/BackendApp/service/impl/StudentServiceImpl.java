package com.etfbl.ip.BackendApp.service.impl;

import com.etfbl.ip.BackendApp.model.Role;
import com.etfbl.ip.BackendApp.model.Student;
import com.etfbl.ip.BackendApp.model.User;
import com.etfbl.ip.BackendApp.model.requests.StudentRequest;
import com.etfbl.ip.BackendApp.model.respons.StudentResponse;
import com.etfbl.ip.BackendApp.repository.RoleRepository;
import com.etfbl.ip.BackendApp.repository.StudentRepository;
import com.etfbl.ip.BackendApp.repository.UserRepository;
import com.etfbl.ip.BackendApp.service.StudentService;
import com.etfbl.ip.BackendApp.util.PasswordUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student update(String index, StudentRequest studentRequest) {

        Student existing = studentRepository.findByIndexNumber(index);
       // existing.setIndexNumber(studentRequest.getIndexNumber());
        existing.setFirstName(studentRequest.getFirstName());
        existing.setLastName(studentRequest.getLastName());
        existing.setYearOfStudy(studentRequest.getYearOfStudy());

        User user = userRepository.findByUsername(studentRequest.getUsername());

        existing.setUser(user);

        return  studentRepository.save(existing);

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
    public List<StudentResponse> findAllForFaculty() {
        List<Student> students = studentRepository.findAll();
        List<StudentResponse> studentResponses = new ArrayList<>();

        for(int i = 0; i < students.size(); i++){
            User u = userRepository.findByUsername(students.get(i).getFirstName());
            StudentResponse response = new StudentResponse();
            response.setFirstName(students.get(i).getFirstName());
            response.setLastName(students.get(i).getLastName());
            response.setIndexNumber(students.get(i).getIndexNumber());
            response.setYearOfStudy(students.get(i).getYearOfStudy());
            response.setId(students.get(i).getId());
            response.setUsername(u.getUsername());
            response.setPassword(u.getPassword());
            studentResponses.add(response);
        }

        return studentResponses;
    }

    @Override
    public void delete(String index) {
        studentRepository.deleteByIndexNumber(index);
    }

    @Override
    public void createStudent(StudentRequest studentRequest) {
        if(userRepository.existsByUsername(studentRequest.getUsername())){
            throw new RuntimeException("Username already exists");
        }

        Role role = roleRepository.findByName("STUDENT");

        User user = new User();
        user.setUsername(studentRequest.getUsername());
        user.setPassword(PasswordUtil.hashPassword(studentRequest.getPassword()));
        user.setRole(role);

        userRepository.save(user);

        Student student = new Student();
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setIndexNumber(studentRequest.getIndexNumber());
        student.setYearOfStudy(studentRequest.getYearOfStudy());
        student.setUser(user);

        studentRepository.save(student);
    }
}
