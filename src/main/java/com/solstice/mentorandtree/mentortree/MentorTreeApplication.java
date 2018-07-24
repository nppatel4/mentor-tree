package com.solstice.mentorandtree.mentortree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableTransactionManagement
@EnableFeignClients
public class MentorTreeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MentorTreeApplication.class, args);
    }
}
