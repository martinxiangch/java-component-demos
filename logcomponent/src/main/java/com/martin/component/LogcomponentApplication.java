package com.martin.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LogcomponentApplication implements CommandLineRunner {

	private static Logger logger= LoggerFactory.getLogger(LogcomponentApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(LogcomponentApplication.class, args);
	}
	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("This is {} {}", 12,"demo1 debug");
		logger.info("This is {} {}", 12,"demo2 info");
		logger.trace("This is {} {}", 12,"demo2 trace");
		logger.error("This is {} {}", 12,"demo2 error");
	}
}
