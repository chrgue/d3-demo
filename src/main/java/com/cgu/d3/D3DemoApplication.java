package com.cgu.d3;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.cgu.d3.service.DependencyService;

@SpringBootApplication
public class D3DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(D3DemoApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner runner(DependencyService service){
		return (args)-> {
			service.generate();
		};
	}
}
