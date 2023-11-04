package ru.egar.myOrg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MyOrgApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyOrgApplication.class, args);
	}

}
