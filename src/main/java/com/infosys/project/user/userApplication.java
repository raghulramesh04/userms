package com.infosys.project.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class userApplication {
	public static void main(String[] args) {
		System.out.println("before controller");
		SpringApplication.run(userApplication.class, args);
		System.out.println("after controller");
		
	}

}
