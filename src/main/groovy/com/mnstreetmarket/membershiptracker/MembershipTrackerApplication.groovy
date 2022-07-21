package com.mnstreetmarket.membershiptracker

import groovy.util.logging.Slf4j
import io.github.cdimascio.dotenv.Dotenv
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@Slf4j
@SpringBootApplication
class MembershipTrackerApplication {

	static void main(String[] args) {
		SpringApplication.run(MembershipTrackerApplication, args)
		log.info("application started at http://localhost:8080")
	}
	
	@Bean
	Dotenv dotenv() {
		return Dotenv.load() 
	}

}
