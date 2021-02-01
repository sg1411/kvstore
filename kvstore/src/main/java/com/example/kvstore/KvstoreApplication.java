package com.example.kvstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan({"com.example.kvstore"})
@EnableScheduling
@SpringBootApplication
public class KvstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(KvstoreApplication.class, args);
	}

}
