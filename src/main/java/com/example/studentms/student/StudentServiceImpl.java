package com.example.studentms.student;

import com.example.studentms.student.dto.Course;
import com.example.studentms.student.dto.StudentInfo;
import com.example.studentms.student.dto.StudentResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;
    private final ApiCall apiCall;
    private final ApiUsingWebClient apiUsingWebClient;
    private final ModelMapper modelMapper;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, ApiCall apiCall, ApiUsingWebClient apiUsingWebClient, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.apiCall = apiCall;
        this.apiUsingWebClient = apiUsingWebClient;
        this.modelMapper = modelMapper;
    }

    @Override
    public StudentResponse allStudents() {
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setStudents(studentRepository.findAll());
        return studentResponse;
    }

    @Override
    public StudentResponse addStudent(Student student) {
        StudentResponse studentResponse = new StudentResponse();
        return modelMapper.map(studentRepository.save(student), StudentResponse.class);
    }

    @Override
    public StudentInfo getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow( () -> new IllegalStateException("not found"));
        Course course = apiCall.getCourseDetails(student.getCourseId());
        StudentInfo studentInfo = new StudentInfo();
        studentInfo.setCourse(course);
        modelMapper.map(student, studentInfo);
        return studentInfo;
    }

    @Override
    public StudentInfo secondGetStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow( () -> new IllegalStateException("not found"));
        Course course = apiUsingWebClient.getCourseDetails(student.getCourseId()).block();
        StudentInfo studentInfo = new StudentInfo();
        studentInfo.setCourse(course);
//        modelMapper.map(student, studentInfo);
        BeanUtils.copyProperties(student, studentInfo);
        return studentInfo;
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
