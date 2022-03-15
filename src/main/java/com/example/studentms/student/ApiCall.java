package com.example.studentms.student;

import com.example.studentms.student.dto.Course;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class ApiCall {

    private final RestTemplate restTemplate;

    private static final String courseBaseUrl = "http://course-service";

    @Autowired
    public ApiCall(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @CircuitBreaker(name = "example", fallbackMethod = "fallback")
    public Course getCourseDetails(int courseId){
        log.info("Calling course microservice API using restTemplate");
        return restTemplate.getForObject(courseBaseUrl + "/course/ID/{courseId}",
                Course.class, courseId);
    }

    public Course fallback(Exception ex){
        log.info("Calling fallback and returning default data");
        Course course = new Course();
        course.setCourseName("common-course");
        course.setDuration(1);
        return course;
    }
}
