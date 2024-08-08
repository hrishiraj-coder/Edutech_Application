package com.GradSkool.Application_Management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApplicationManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationManagementApplication.class, args);
	}

}
