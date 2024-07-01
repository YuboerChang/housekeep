package com.coolers.housekeep.housekeep;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.coolers.housekeep.housekeep.dao")
@SpringBootApplication
public class HousekeepApplication {

	public static void main(String[] args) {
		SpringApplication.run(HousekeepApplication.class, args);
	}

}
