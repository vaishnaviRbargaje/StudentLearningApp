package com.tka.StudentLearningApp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CrosConfig {

	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
            	 registry.addMapping("/**") 
                 .allowedOrigins("http://localhost:3000") 
                 .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") 
                 .allowedOrigins("https://edu-nexus-front-end-v2.vercel.app")
                 .allowedHeaders("*") 
                 .exposedHeaders("Authorization") 
                 .allowCredentials(true); 
	            }
	        };
	    }
}
