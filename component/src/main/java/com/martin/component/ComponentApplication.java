package com.martin.component;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
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
		
		TestCommonCodec();
		TestPBE();
	}
	
	@Bean
	public jacksonDemo getJasksoDemo() {
		return new jacksonDemo();
	}
	
	
	
//	@Bean
//	public HttpClientDemo getHttpClientDemo() {
//		return new HttpClientDemo();
//	}
	
	@SuppressWarnings("unused")
	private void TestCommonCodec() {
		logger.info("begin to test codec");
		String name="name=martin&&value=123456";
		try {
			String encodeString=CommonCodec.base64Encode(name);
			logger.info("Source:{} , Encode:{}",name,encodeString);
			String decodeString=CommonCodec.base64Decode(encodeString);
			logger.info("Source:{} , Decode:{}",encodeString,decodeString);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private void TestPBE() {
		String str="this ia a new password";
        String password="martin";
        
        logger.info("Source："+str);
        logger.info("Password"+password);

        byte[] salt;
		try {
			salt = PBECoder.initSalt();
			logger.info("salt:"+Base64.encodeBase64String(salt));
	        
	        byte[] data=PBECoder.encrypt(str.getBytes(), password, salt);
	        logger.info("encrypt:"+Base64.encodeBase64String(data));
	  
	        data=PBECoder.decrypt(data, password, salt);
	        logger.info("decrypt:"+new String(data));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
