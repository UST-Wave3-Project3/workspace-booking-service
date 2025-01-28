package com.project.workspace_booking_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WorkspaceBookingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkspaceBookingServiceApplication.class, args);
	}

}
