package com.example.studentms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
//@PropertySources({
//        @PropertySource({"file:///Users/mac/Desktop/Setup/student.properties"})
//})
public class StudentMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentMsApplication.class, args);
    }

}
