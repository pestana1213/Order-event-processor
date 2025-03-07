package com.example.order_events_processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.order_events_processor")
public class OrderEventsProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderEventsProcessorApplication.class, args);
	}

}
