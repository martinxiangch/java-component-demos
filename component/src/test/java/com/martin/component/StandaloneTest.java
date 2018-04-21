package com.martin.component;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
//@WebMvcTest(MRestController.class)
public class StandaloneTest {

	private MockMvc mvc;
	
	@Mock
	private jacksonDemo jsonDemo;
	
	@InjectMocks
	private MRestController restctrl;
	
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	    mvc=MockMvcBuilders.standaloneSetup(restctrl).build();	
	}
	
	@Test
	public void MResttest() throws Exception {
		given(this.jsonDemo.getTest()).willReturn("mvc-test");
		mvc.perform(get("/test").accept(MediaType.TEXT_PLAIN))
		.andExpect(status().isOk()).andExpect(content().string("mvc-test"));
	}
	
}
