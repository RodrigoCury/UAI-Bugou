package br.dev.rodrigocury.uaibugouapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class UaiBugouApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UaiBugouApiApplication.class, args);
	}

}
