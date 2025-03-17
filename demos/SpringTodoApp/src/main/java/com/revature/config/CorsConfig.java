package com.revature.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    // This configuration allows all origins using allowedOriginPatterns and
    // enables credentials to be included in requests.
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
//                        .allowedOriginPatterns("*") // allows any origin dynamically when credentials are used
                        .allowedOriginPatterns("http://127.0.0.1:5500") // Use the exact origin your front-end is served from
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);    // enable credentials
            }
        };
    }
}
