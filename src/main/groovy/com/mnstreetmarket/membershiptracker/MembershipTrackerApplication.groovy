package com.mnstreetmarket.membershiptracker

import groovy.util.logging.Slf4j
import io.github.cdimascio.dotenv.Dotenv
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled

@Slf4j
@EnableScheduling
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

	@Scheduled(fixedRate = 60000L)
	void ping() {
		// hack to keep the heroku dyno from going to "sleep"
		String text = new URL("http://portal.mnstreetmarket.com/keep-alive.json").text
		log.info("keep alive connection: $text")
	}

}
