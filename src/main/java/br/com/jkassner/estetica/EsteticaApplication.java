package br.com.jkassner.estetica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class EsteticaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EsteticaApplication.class, args);
	}
}
