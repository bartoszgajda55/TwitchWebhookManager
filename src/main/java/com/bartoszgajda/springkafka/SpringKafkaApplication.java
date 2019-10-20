package com.bartoszgajda.springkafka;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

@SpringBootApplication
public class SpringKafkaApplication {
	private static final String MEETUP_RSVP_ENDPOINT = "ws://stream.meetup.com/2/rsvps";

	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaApplication.class, args);
	}

	@Bean
	public ApplicationRunner initializeConnection(CollectionSocketHandler collectionSocketHandler) {
		return args -> {
			WebSocketClient socketClient = new StandardWebSocketClient();
			socketClient.doHandshake(collectionSocketHandler, MEETUP_RSVP_ENDPOINT);
		};
	}
}
