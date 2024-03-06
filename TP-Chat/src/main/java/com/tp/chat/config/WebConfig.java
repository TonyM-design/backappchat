package com.tp.chat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

  @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return objectMapper;
    }
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") // Configure which paths should allow CORS
				.allowedOrigins("http://localhost:4200") // Allow requests from this origin
				.allowedMethods("GET", "POST", "PUT", "DELETE") // Allowed HTTP methods
				.allowCredentials(true); // Allow sending cookies along with requests if needed
	}
}
