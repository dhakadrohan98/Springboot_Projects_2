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

		SpringApplication.run(ClientApplication.class, args);
		ConfigurableApplicationContext ctx = new
				SpringApplicationBuilder(ClientApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);

		WebClient loadBalanacedClient = ctx.getBean(WebClient.Builder.class).build();

		for(int i = 0; i < 10; i++) {
			String response = loadBalanacedClient.get().uri("http://localhost:8086/hello")
					.retrieve().toEntity(String.class)
					.block().getBody();
			System.out.println(response);
		}
	}

}
