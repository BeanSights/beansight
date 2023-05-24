package com.ll.beansight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BeansightApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeansightApplication.class, args);
    }

}
