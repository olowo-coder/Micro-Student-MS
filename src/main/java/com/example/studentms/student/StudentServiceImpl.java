package com.example.studentms.student;

import com.example.studentms.student.dto.StudentResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public StudentResponse allStudents() {
        return StudentResponse.builder().students(studentRepository.findAll()).build();
    }

    @Override
    public StudentResponse addStudent(Student student) {
        return modelMapper.map(studentRepository.save(student), StudentResponse.class);
    }

    @Override
    public StudentResponse getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow( () -> new IllegalStateException("not found"));
        return modelMapper.map(student, StudentResponse.class);
    }

    @Override
    public StudentResponse getStudentByName(String name) {
        Student student = studentRepository.findByName(name)
                .orElseThrow( () -> new IllegalStateException("not found"));
        return modelMapper.map(student, StudentResponse.class);
    }

    @Override
    public StudentResponse updateStudent(Student student) {
        return modelMapper.map(studentRepository.save(student), StudentResponse.class);
    }

    @Override
    public boolean deleteStudent(Long id) {
        studentRepository.deleteById(id);
        return true ;
    }
}
