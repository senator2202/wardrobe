package com.example.wardrobe.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.example.wardrobe.app.controller",
        "com.example.wardrobe.app.service",
        "com.example.wardrobe.app.model.repository",
        "com.example.wardrobe.app.config",
        "com.example.wardrobe.app.auth"
})
@EntityScan(basePackages = {
        "com.example.wardrobe.app.model.entity",
        "com.example.wardrobe.app.auth"
})
@EnableJpaRepositories(basePackages = {
        "com.example.wardrobe.app.model.repository",
        "com.example.wardrobe.app.auth"
})
public class WardrobeApplication {

    public static void main(String[] args) {
        SpringApplication.run(WardrobeApplication.class, args);
    }
}
