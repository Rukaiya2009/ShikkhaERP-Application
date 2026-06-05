package com.shikkhaerp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.ZoneOffset;
import java.util.TimeZone;

@SpringBootApplication
@EnableConfigurationProperties
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableTransactionManagement
@EnableCaching
@EnableAsync
@EnableScheduling
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
@OpenAPIDefinition(
    info = @Info(
        title = "ShikkhaERP API",
        version = "1.0.0",
        description = "Enterprise SaaS ERP System for Educational Institutions\n\n" +
                      "## Features\n" +
                      "- Multi-tenant architecture\n" +
                      "- Role-based access control (RBAC)\n" +
                      "- Permission-based access control (PBAC)\n" +
                      "- Real-time notifications\n" +
                      "- Audit logging\n" +
                      "- Automated fee collection\n" +
                      "- Attendance management\n" +
                      "- Examination management\n" +
                      "- Learning management system (LMS)\n" +
                      "- Library management\n" +
                      "- Transport management\n" +
                      "- HR & Payroll management\n" +
                      "- Comprehensive reporting\n\n" +
                      "## Authentication\n" +
                      "Use Bearer token authentication. Obtain token from `/api/v1/auth/login`",
        contact = @Contact(
            name = "ShikkhaERP Support",
            email = "support@shikkhaerp.com",
            url = "https://shikkhaerp.com"
        ),
        license = @License(
            name = "Commercial License",
            url = "https://shikkhaerp.com/license"
        ),
        termsOfService = "https://shikkhaerp.com/terms"
    ),
    servers = {
        @Server(
            description = "Development Server",
            url = "http://localhost:8080"
        ),
        @Server(
            description = "Production Server",
            url = "https://api.shikkhaerp.com"
        )
    }
)
public class ShikkhaErpApplication {

    private static final Logger logger = LoggerFactory.getLogger(ShikkhaErpApplication.class);

    @PostConstruct
    public void init() {
        // Set default timezone to UTC for consistency
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneOffset.UTC));
        
        // Log application startup information
        logger.info("========================================");
        logger.info("🚀 ShikkhaERP Application Starting...");
        logger.info("========================================");
        logger.info("📅 Timezone: {}", TimeZone.getDefault().getDisplayName());
        logger.info("🌍 Environment: {}", System.getProperty("spring.profiles.active", "default"));
        logger.info("🖥️ Java Version: {}", System.getProperty("java.version"));
        logger.info("========================================");
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ShikkhaErpApplication.class);
        
        // Set additional application properties programmatically if needed
        app.setAddCommandLineProperties(true);
        
        // Run the application
        ConfigurableApplicationContext context = app.run(args);
        
        // Log successful startup
        logger.info("========================================");
        logger.info("✅ ShikkhaERP Application Started Successfully!");
        logger.info("========================================");
        logger.info("📚 API Documentation: http://localhost:8080/api/swagger-ui.html");
        logger.info("📊 Actuator Endpoints: http://localhost:8080/api/actuator");
        logger.info("========================================");
        
        // Register shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("========================================");
            logger.info("🛑 ShikkhaERP Application Shutting Down...");
            logger.info("========================================");
        }));
    }
}