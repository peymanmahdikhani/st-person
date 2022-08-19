package com.example.interviewperson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class InterviewPersonApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterviewPersonApplication.class, args);
    }

}
