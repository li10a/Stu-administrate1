package com.stu.administrate.controller.teacher;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stu.administrate.model.Homework;
import com.stu.administrate.model.User;
import com.stu.administrate.service.ClassService;
import com.stu.administrate.service.HomeworkService;
import com.stu.administrate.type.ForwardPageType;

@Controller
@RequestMapping("/teacher")
public class TeacherHomeworkController {

	private Logger logger = LoggerFactory.getLogger(TeacherHomeworkController.class);

	@Autowired
	ClassService classService;

	@Autowired
	HomeworkService homeworkService;

	@Autowired
	@Qualifier("propertyConfigurer")
	private PropertiesFactoryBean propertyConfigurer;

	@GetMapping("/homeworkList")
	public String homeworkList(Model model, @RequestAttribute("loginUser") User loginUser) {
		model.addAttribute("homeworkList", homeworkService.selectAllHomework(loginUser.getId()));
		return "/teacher/homeworkList";
	}

	@GetMapping("/addHomeworkForm")
	public String addHomeworkForm(Model model, @RequestParam("classNo") int classNo) {
		model.addAttribute("classInfo", classService.selectClassByNo(classNo));
		return "/teacher/addHomework";
	}

	@PostMapping("/addHomework")
	public String addHomework(Model model, @RequestParam("title") String title, @RequestParam("task") String task,
			@RequestParam("classNo") int classNo, @RequestAttribute("loginUser") User user) {
		homeworkService.insertHomework(title, task, classNo, user.getId());
		model.addAttribute("url", "/teacher/homeworkList");
		try {
			model.addAttribute("msg", propertyConfigurer.getObject().getProperty("success.admin.add"));
		} catch (IOException e) {
			logger.error("message key error!");
		}
		return ForwardPageType.FORWARD_GOPAGE.getForwardPage();
	}

	@GetMapping("/modifyHomeworkForm")
	public String goModifyHomeworkForm(Model model, @RequestParam("no") int no) {
		Homework homework = homeworkService.selectHomeworkByNo(no);
		model.addAttribute("homeworkInfo", homework);
		model.addAttribute("classInfo", classService.selectClassByNo(homework.getClassNo()));
		return "/teacher/modifyHomework";
	}

	@PostMapping("/modifyHomework")
	public String modifyHomework(Model model, @RequestParam("no") int no, @RequestParam("title") String title, @RequestParam("task") String task) {
		homeworkService.updateHomeworkByNo(no, title, task);
		model.addAttribute("url", "/teacher/homeworkList");
		try {
			model.addAttribute("msg", propertyConfigurer.getObject().getProperty("success.admin.modify"));
		} catch (IOException e) {
			logger.error("message key error!");
		}
		return ForwardPageType.FORWARD_GOPAGE.getForwardPage();
	}

	@GetMapping("/deleteHomework")
	public String deleteHomework(Model model, @RequestParam("no") int no) {
		homeworkService.deleteHomeworkByNo(no);
		model.addAttribute("url", "/teacher/homeworkList");
		try {
			model.addAttribute("msg", propertyConfigurer.getObject().getProperty("success.admin.delete"));
		} catch (IOException e) {
			logger.error("message key error!");
		}
		return ForwardPageType.FORWARD_GOPAGE.getForwardPage();
	}
}
