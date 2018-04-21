package com.martin.component;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class jacksonDemo {
	
	Logger logger= LoggerFactory.getLogger(jacksonDemo.class);
	 

	public void Test() throws Exception {
		User aa=new User();
		aa.setAge(11);
		aa.setBirthday(new Date());
		aa.setEmail("martin@igt.com");
		aa.setName("Martin");
		ObjectMapper mapper=new ObjectMapper();
		String msg=mapper.writeValueAsString(aa);
		logger.info("Parsed Msg： {}",msg);
		
		User bb= mapper.readValue(msg, User.class);
		if(bb.getEmail().equals(aa.getEmail())&&bb.getName().equals(aa.getName())) {
			logger.info("Success to recove the User object");
		}
		
	}
	
	
	
	public String getTest() {
		return "jacksonDemo-test";
	}

}
