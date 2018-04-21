package com.martin.component;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class ComponentApplicationTests {

	@MockBean
	private jacksonDemo jsonDemo;
	
	@Autowired
	private MRestController restctrl;
	
	@Autowired
	private MockMvc mvc;
	
	@Before
	public void setUp() {
	    Mockito.when(jsonDemo.getTest())
	      .thenReturn("Mock-test");
	}
	
	
	@Test
	public void MResttest() {
	   assertThat(restctrl.test()).isEqualTo("Mock-test");
	}

	@Test
	public void MVCTest() throws Exception {
		given(this.jsonDemo.getTest()).willReturn("mvc-test");
		this.mvc.perform(get("/test").accept(MediaType.TEXT_PLAIN))
		.andExpect(status().isOk()).andExpect(content().string("mvc-test"));
		
	}
	
	
}
