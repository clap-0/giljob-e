package com.likelion.giljobe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GiljobEApplication {

	public static void main(String[] args) {
		SpringApplication.run(GiljobEApplication.class, args);
	}

}
