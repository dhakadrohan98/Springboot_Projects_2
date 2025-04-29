package com.weather;

import lombok.extern.slf4j.Slf4j;
import org.beans.container.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"org.beans.container", "com.weather"})
@Slf4j
public class WeatherApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(WeatherApplication.class, args);
		Student stud = context.getBean(Student.class);
		log.info(stud.toString());
	}

}
