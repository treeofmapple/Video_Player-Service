package com.tom.service.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class VideoStorageApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideoStorageApplication.class, args);
	}

}
