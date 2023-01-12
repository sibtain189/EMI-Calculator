package com.connection.Practice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.connection.Practice.controller.EmiController;
import com.connection.Practice.model.EmiResponseModel;
import com.connection.Practice.service.impl.EmiService;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.core.net.ObjectWriter;

@RunWith(MockitoJUnitRunner.class)
public class EmiServiceTest {
	
	private MockMvc mockMvc;
	
	@Mock
	private EmiService service;
	
	@InjectMocks
	private EmiController emiController;
	
	ObjectMapper objectMapper=new ObjectMapper();
	ObjectWriter objectWriter= (ObjectWriter) objectMapper.writer();
	
	EmiResponseModel responseModel=new EmiResponseModel();
	
	@BeforeEach
	void setUp() {
		
	}

}
