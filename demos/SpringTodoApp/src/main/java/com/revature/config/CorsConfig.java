package com.revature.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    // This bean configures global CORS settings.
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Apply CORS configuration to all endpoints
                registry.addMapping("/**")
                        // Specify allowed origins. Replace with your front-end's URL.
                        .allowedOriginPatterns("http://127.0.0.1:5500", "http://localhost:5173")
                        // Allow specific HTTP methods
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        // Allow all headers
                        .allowedHeaders("*")
                        // Allow credentials (cookies, authorization headers, etc.)
                        .allowCredentials(true);
            }
        };
    }
}
