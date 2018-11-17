package com.example.backoffice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "sample")
@Controller
public class SampleController {

	@GetMapping(path = "/")
	public String index() {
		return "sample/index";
	}
}
