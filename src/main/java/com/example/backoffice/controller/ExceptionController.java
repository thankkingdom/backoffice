package com.example.backoffice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ExceptionController {

	@RequestMapping(value="/404")
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String notFound() {
		return "error.html";
	}
	
	@RequestMapping(value="/403")
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public String forbidden() {
		return "error.html";
	}
}
