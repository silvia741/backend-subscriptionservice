package com.adidas.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
@TestInstance(Lifecycle.PER_CLASS)
@WebMvcTest(controllers = PublicController.class)
public class PublicControllerTest {
	
	 	@Autowired
	    private WebApplicationContext wac;

	    private MockMvc mockMvc;

	    @InjectMocks
	    private PublicController ac;
	    
	    @BeforeEach // For Junit5
	    public void setup() {
	        mockMvc = MockMvcBuilders.standaloneSetup(ac).build();

	    }

}
