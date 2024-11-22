package com.kilpi.finayo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class LoanServiceApplication {
//	@PostConstruct
//	public void init(){
//		// Setting Spring Boot SetTimeZone
//		TimeZone.setDefault(TimeZone.getTimeZone("IST"));
//	}
	public static void main(String[] args) {
		
		SpringApplication.run(LoanServiceApplication.class, args);
	}

}
