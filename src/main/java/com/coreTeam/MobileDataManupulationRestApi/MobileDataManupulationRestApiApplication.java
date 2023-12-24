package com.coreTeam.MobileDataManupulationRestApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MobileDataManupulationRestApiApplication {

	public String PORT=System.getenv("PORT");
	public static void main(String[] args) {
		SpringApplication.run(MobileDataManupulationRestApiApplication.class, args);
	}

}
