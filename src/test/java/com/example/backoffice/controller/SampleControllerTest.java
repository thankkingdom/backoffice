package com.example.backoffice.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.example.backoffice.service.LoginUserDetails;
import com.example.backoffice.service.LoginUserDetailsService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
public class SampleControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private LoginUserDetailsService loginUserDetailsService;

	@Test
	public void index() throws Exception {

		LoginUserDetails userDetails = (LoginUserDetails) loginUserDetailsService.loadUserByUsername("admin1");

		//@formatter:off
		mockMvc.perform(get("http://localhost:8080/sample/")
			.contentType(MediaType.TEXT_HTML)
			.with(SecurityMockMvcRequestPostProcessors.csrf())
			.with(user(userDetails))
		)
		.andExpect(status().is2xxSuccessful());
		//@formatter:on
	}
}
