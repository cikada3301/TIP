package edu.bru.graduatework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BasketMicroService {
    public static void main(String[] args) {
        SpringApplication.run(BasketMicroService.class, args);
    }
}