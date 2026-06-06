package com.shikkhaerp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ShikkhaErpApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShikkhaErpApplication.class, args);
        System.out.println("========================================");
        System.out.println("🚀 ShikkhaERP Application Started!");
        System.out.println("📚 API: http://localhost:8080/api");
        System.out.println("🗄️  H2 Console: http://localhost:8080/api/h2-console");
        System.out.println("========================================");
    }
}