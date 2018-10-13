package org.ypq.springboothelloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringboothelloworldApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringboothelloworldApplication.class, args);

    }
}

