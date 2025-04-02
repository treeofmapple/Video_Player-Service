package com.tom.service.userstorage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableCaching
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class UserStorageApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserStorageApplication.class, args);
	}

}
