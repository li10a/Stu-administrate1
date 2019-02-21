package com.stu.administrate.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stu.administrate.service.SampleService;

@Controller
public class LoginController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SampleService sampleService;

	@GetMapping("/login")
	public String hello(Model model) {
		return "/login/login";
	}
}
