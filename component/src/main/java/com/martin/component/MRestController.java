package com.martin.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MRestController {
	
	@Autowired
	private jacksonDemo jsondemo;
	
	@RequestMapping("/hello")
	public String index() {
	    return "Hello World";
	}
	
	@RequestMapping("/test")
	public String test() {
		return jsondemo.getTest();
	}
}


