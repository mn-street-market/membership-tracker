package com.mnstreetmarket.membershiptracker

import io.github.cdimascio.dotenv.Dotenv
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class MembershipTrackerApplication {

	static void main(String[] args) {
		SpringApplication.run(MembershipTrackerApplication, args)
	}
	
	@Bean
	Dotenv dotenv() {
		return Dotenv.load() 
	}

}
