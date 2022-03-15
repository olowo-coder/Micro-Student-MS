package com.example.studentms.student;

import com.example.studentms.student.dto.Course;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerClientAutoConfiguration;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ApiUsingWebClient {

    private final WebClient.Builder webClientBuilder;

    private static final String courseBaseUrl = "http://course-service";

    public ApiUsingWebClient(WebClient.Builder webClientBuilder, ReactorLoadBalancerExchangeFilterFunction lbFunction) {
        this.webClientBuilder = webClientBuilder;
    }

    public Mono<Course> getCourseDetails(int courseId){
        return webClientBuilder.build().get().uri(courseBaseUrl + "/course/ID/" + courseId)
                .retrieve().bodyToMono(Course.class);
    }
}
