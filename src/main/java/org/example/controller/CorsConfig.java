package org.example.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")  // 配置允许的接口路径
                        .allowedOrigins("http://localhost:3000")  // 允许的前端域名或IP
                        .allowedMethods("GET", "POST", "PUT", "DELETE")  // 允许的HTTP方法
                        .allowCredentials(true);  // 允许携带跨域请求的凭证（如cookies）
            }
        };
    }
}
