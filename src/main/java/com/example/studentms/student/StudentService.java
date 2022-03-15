package com.example.studentms.student;

import com.example.studentms.student.dto.StudentInfo;
import com.example.studentms.student.dto.StudentResponse;

import java.util.List;

public interface StudentService {

    StudentResponse allStudents();

    StudentResponse addStudent(Student student);

    StudentInfo getStudentById(Long id);

    StudentInfo secondGetStudentById(Long id);

    StudentResponse getStudentByName(String name);

    StudentResponse updateStudent (Student student);

    boolean deleteStudent (Long id);
}
