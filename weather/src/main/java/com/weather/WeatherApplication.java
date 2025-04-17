package com.weather;

import org.beans.container.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan(basePackages = {"org.beans.container"})
public class WeatherApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(WeatherApplication.class, args);
		Student stud = context.getBean(Student.class);
		System.out.println(stud);
	}

}
