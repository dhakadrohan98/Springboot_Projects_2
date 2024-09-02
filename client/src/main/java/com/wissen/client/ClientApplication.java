package com.wissen.client;

import com.wissen.client.config.WebClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.reactive.function.client.WebClient;

/**
 *  Let’s use the WebClient bean from above to send ten requests to the example server:
 */
@SpringBootApplication
public class ClientApplication {

	public static void main(String[] args) {

//		SpringApplication.run(ClientApplication.class, args);
		ConfigurableApplicationContext ctx = new
				SpringApplicationBuilder(ClientApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);

//		WebClient loadBalanacedClient =

		// Create a simple WebClient without load balancing
		WebClient webClient = WebClient.builder().build();
		final String[] serverUrls = {"http://localhost:8080/hello",
				"http://localhost:8081/hello"};

		int counter = 0;
		int len = serverUrls.length;

		for(int i = 1; i <= 20; i++) {

			String tempUrl = serverUrls[counter % len];
			counter++;
			String response = webClient.get().uri(tempUrl)
					.retrieve().toEntity(String.class)
					.block().getBody();
			System.out.println(response);
		}
	}

}
