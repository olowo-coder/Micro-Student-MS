package com.example.studentms.student.dto;

import com.example.studentms.student.Student;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentResponse {

    private Long studentId;
    private String name;
    private String mobileNo;
    private int courseId;
    private List<Student> students;
}
