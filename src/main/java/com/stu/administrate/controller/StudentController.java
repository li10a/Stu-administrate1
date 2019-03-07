package com.stu.administrate.controller;

import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stu.administrate.model.User;
import com.stu.administrate.service.StudentService;

@Controller
@RequestMapping("/admin")
public class StudentController {

	private Logger logger = LoggerFactory.getLogger(StudentController.class);

	@Autowired
	StudentService studentService;

	@Autowired
	@Qualifier("propertyConfigurer")
	private PropertiesFactoryBean propertyConfigurer;

	@GetMapping("/addStudentForm")
	public String addStudentForm(Model model) {
		model.addAttribute("classList", studentService.getAllClass());
		return "/admin/addStudent";
	}

	@PostMapping("/addStudent")
	@ResponseBody
	public String addStudent(Model model, @RequestParam("user") User user, Part file) {
		studentService.addStudent(user, file);
		return "/admin/studentList";
	}
}
