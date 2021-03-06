package com.stu.administrate.controller.teacher;

import java.io.IOException;
import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;

import com.stu.administrate.model.User;
import com.stu.administrate.service.ClassService;
import com.stu.administrate.service.StudentService;
import com.stu.administrate.type.ForwardPageType;
import com.stu.administrate.util.PageInfo;

@Controller
@RequestMapping("/teacher")
public class TeacherStudentController {

	private Logger logger = LoggerFactory.getLogger(TeacherStudentController.class);

	@Autowired
	private StudentService studentService;

	@Autowired
	private ClassService classService;

	@Autowired
	@Qualifier("propertyConfigurer")
	private PropertiesFactoryBean propertyConfigurer;

	@GetMapping("/studentList")
	public String studentList(Model model, @RequestParam(name = "classNo", required = false) Integer classNo,
			@RequestParam(name = "page", defaultValue = "1") int currentPage,
			@RequestAttribute("loginUser") User loginUser) {
		if (classNo == null) {
			classNo = studentService.getMinClassNoByTeacherId(loginUser.getId());
		}
		model.addAttribute("classList", classService.selectClassByTeacherId(loginUser.getId()));
		List<User> studentList = studentService.selectAllStudentByClassNo(classNo);
		PageInfo pageInfo = new PageInfo(currentPage, studentList.size());
		model.addAttribute("studentList", studentList.subList(pageInfo.getStartRow() - 1, pageInfo.getEndRow()));
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("classNo", classNo);
		return "/teacher/studentList";
	}

	@GetMapping("/addStudentForm")
	public String addStudentForm(Model model, @RequestAttribute("loginUser") User loginUser) {
		model.addAttribute("classList", classService.selectClassByTeacherId(loginUser.getId()));
		return "/teacher/addStudent";
	}

	@PostMapping("/addStudent")
	public String addStudent(Model model, User user, @RequestParam("file") MultipartFile file) {
		studentService.addStudent(user, file);
		model.addAttribute("url", "/teacher/studentList?classNo=" + user.getClassNo());
		try {
			model.addAttribute("msg", propertyConfigurer.getObject().getProperty("success.admin.add"));
		} catch (IOException e) {
			logger.error("message key error!");
		}
		return ForwardPageType.FORWARD_GOPAGE.getForwardPage();
	}

	@GetMapping("/deleteStudent")
	public String deleteStudent(Model model, @RequestParam("id") String id, @RequestParam("classNo") String classNo) {
		studentService.deleteStudentById(id);
		try {
			model.addAttribute("msg", propertyConfigurer.getObject().getProperty("success.admin.delete"));
		} catch (IOException e) {
			// do nothing
		}
		model.addAttribute("url", "/teacher/studentList?classNo=" + classNo);
		return ForwardPageType.FORWARD_GOPAGE.getForwardPage();

	}

	@GetMapping("/modifyStudentForm")
	public String modifyStudentForm(Model model, @RequestParam("id") String id) {
		model.addAttribute("studentInfo", studentService.selectStudentById(id));
		model.addAttribute("classList", studentService.getAllClass());
		return "/teacher/modifyStudent";
	}

	@PostMapping("/modifyStudent")
	public String modifyStudent(Model model, User user, @RequestParam("file") MultipartFile file) {
		studentService.updateStudent(user, file);
		model.addAttribute("url", "/teacher/studentList?classNo=" + user.getClassNo());
		try {
			model.addAttribute("msg", propertyConfigurer.getObject().getProperty("success.admin.update"));
		} catch (IOException e) {
			logger.error("message key error!");
		}
		return ForwardPageType.FORWARD_GOPAGE.getForwardPage();
	}
}
