package com.etfbl.ip.BackendApp.controller;

import com.etfbl.ip.BackendApp.model.Student;
import com.etfbl.ip.BackendApp.model.User;
import com.etfbl.ip.BackendApp.model.requests.StudentRequest;
import com.etfbl.ip.BackendApp.service.StudentService;
import com.etfbl.ip.BackendApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final UserService userService;

    @PostMapping
    public Student create(@RequestBody StudentRequest studentRequest){
        User user = userService.findByUsername(studentRequest.getUsername());
        Student student = new Student();
        student.setUser(user);
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setIndexNumber(studentRequest.getIndexNumber());
        student.setYearOfStudy(studentRequest.getYearOfStudy());
        return studentService.save(student);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable Integer id, @RequestBody StudentRequest studentRequest){
        User user = userService.findByUsername(studentRequest.getUsername());
        Student student = new Student();
        student.setUser(user);
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setIndexNumber(studentRequest.getIndexNumber());
        student.setYearOfStudy(studentRequest.getYearOfStudy());
        return studentService.update(id, student);
    }

    @GetMapping
    public List<Student> getAll(){
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable Integer id){
        return studentService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        studentService.delete(id);
    }
}
