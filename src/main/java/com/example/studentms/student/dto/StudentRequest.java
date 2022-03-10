package com.example.studentms.student.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentRequest {
    private Long studentId;
    private String name;
    private String mobileNo;
    private int courseId;
}
