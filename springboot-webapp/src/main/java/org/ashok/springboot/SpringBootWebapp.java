package org.ashok.springboot;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootWebapp {
	
	public static void main(String[] args) {
		
		SpringApplication.run(SpringBootWebapp.class, args);
			
	}
	
	@Bean
	public InitializingBean runner(DataSource dataSource) {
		return () -> {
			System.out.println("******This Spring Boot app is using datasource class = " + dataSource.getClass().getCanonicalName());
		};
	}

}
