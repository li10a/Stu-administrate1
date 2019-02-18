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
public class SampleController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SampleService sampleService;

	@GetMapping("page")
	public String hello(@RequestParam String name, Model model) {
		logger.info(name);
		model.addAttribute("name", name);
		return "sample";
	}

	@GetMapping("pageWithData")
	public String pageWithData(Model model) {
		int count = sampleService.getTotalArticleCount();
		logger.info(String.valueOf(count));
		model.addAttribute("name", String.valueOf(count));
		return "sample";
	}
}
