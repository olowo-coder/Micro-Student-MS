package com.example.studentms.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@RefreshScope
public class StudentController {

    private final StudentService studentService;

    @Value("${message:default message}")
    private String message;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/message")
    public String message(){
        return message;
    }

    @GetMapping("/all")
    public ResponseEntity<?> fetchAll(){
       return ResponseEntity.ok(studentService.allStudents());
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> saveStudent(@RequestBody Student student){
        return ResponseEntity.ok(studentService.addStudent(student));
    }

    @GetMapping("/ID/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @GetMapping("/ID/v2/{id}")
    public ResponseEntity<?> secondGetById(@PathVariable Long id){
        return ResponseEntity.ok(studentService.secondGetStudentById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getById(@PathVariable String name){
        return ResponseEntity.ok(studentService.getStudentByName(name));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateStudent(@RequestBody Student student){
        return ResponseEntity.ok(studentService.updateStudent(student));
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id){
        return ResponseEntity.ok(studentService.deleteStudent(id));
    }
}
