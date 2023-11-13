package com.openmemo.opmback;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.openmemo.opmback")
public class OpmbackApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpmbackApplication.class, args);
	}

}
