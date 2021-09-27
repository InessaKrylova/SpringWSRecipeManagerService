package com.recipemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;

@SpringBootApplication
public class RecipeServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(RecipeServiceApplication.class, args);
	}
}
