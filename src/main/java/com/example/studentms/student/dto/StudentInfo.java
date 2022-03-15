package com.example.studentms.student.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class StudentInfo {
    private Long studentId;
    private String name;
    private String mobileNo;
    private int courseId;
    private Course course;
}
