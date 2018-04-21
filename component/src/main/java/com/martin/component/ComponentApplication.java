package com.martin.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ComponentApplication implements CommandLineRunner {

	@Autowired
	private HttpClientDemo httpClientDemo;
	
	@Autowired
	private JAXBDemo jaxbDemo;
	
	private static Logger logger= LoggerFactory.getLogger(ComponentApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ComponentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*
		logger.debug("This is {} {}", 12,"demo1 debug");
		logger.info("This is {} {}", 12,"demo2 info");
		logger.trace("This is {} {}", 12,"demo2 trace");
		logger.error("This is {} {}", 12,"demo2 error");
		
		// jaskson demo
		jacksonDemo demo=new jacksonDemo();
		demo.Test();
		*/
		
		//httpclient demo
		//httpClientDemo.testGet();
		
		//jaxb XML demo
		jaxbDemo.Test();
	}
	
	@Bean
	public jacksonDemo getJasksoDemo() {
		return new jacksonDemo();
	}
	
//	@Bean
//	public HttpClientDemo getHttpClientDemo() {
//		return new HttpClientDemo();
//	}
}
