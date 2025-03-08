package com.codeship.autosysjob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AutosysJobApplication {

	private static final Logger log = LoggerFactory.getLogger(AutosysJobApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AutosysJobApplication.class, args);
	}

}
