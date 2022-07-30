package com.blog;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BloggingWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloggingWebAppApplication.class, args);
	}
	
	@Bean
	ModelMapper modelmapper()
	{
		return new ModelMapper();
	}

}
