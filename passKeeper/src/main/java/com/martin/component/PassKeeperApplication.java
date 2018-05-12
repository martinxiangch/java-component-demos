package com.martin.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.martin.component.service.UserService;

@SpringBootApplication
public class PassKeeperApplication implements CommandLineRunner {

	private static Logger logger= LoggerFactory.getLogger(PassKeeperApplication.class);
	
	@Autowired
    UserService userService;
	
	public static void main(String[] args) {
		SpringApplication.run(PassKeeperApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		logger.info("begin to monitor passkeeper");
	}
}
