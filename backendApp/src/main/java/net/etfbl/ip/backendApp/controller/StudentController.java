package net.etfbl.ip.backendApp.controller;

import lombok.RequiredArgsConstructor;
import net.etfbl.ip.backendApp.model.Student;
import net.etfbl.ip.backendApp.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public Student create(@RequestBody Student student) {
        return studentService.save(student);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @RequestBody Student student){
        return studentService.update(id, student);
    }


    @GetMapping
    public List<Student> getAll(){
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable Long id){
        return studentService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        studentService.delete(id);
    }

}
