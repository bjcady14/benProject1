package com.revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication
public class SpringDataDemo1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataDemo1Application.class, args);
	}
}
