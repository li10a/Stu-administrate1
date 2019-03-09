package com.stu.administrate.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.stu.administrate.constants.ServerFolderConstant;
import com.stu.administrate.model.User;
import com.stu.administrate.service.StudentService;
import com.stu.administrate.type.ForwardPageType;
import com.stu.administrate.util.PageInfo;

@Controller
@RequestMapping("/admin")
public class AdminStudentController {

	private Logger logger = LoggerFactory.getLogger(AdminStudentController.class);

	@Autowired
	StudentService studentService;

	@Autowired
	@Qualifier("propertyConfigurer")
	private PropertiesFactoryBean propertyConfigurer;

	@GetMapping("/studentList")
	public String studentList(Model model, @RequestParam(name = "classNo", required = false) Integer classNo,
			@RequestParam(name = "page", defaultValue = "1") int currentPage) {
		if (classNo == null) {
			classNo = studentService.getMinClassNo();
		}
		model.addAttribute("classList", studentService.getAllClass());
		List<User> studentList = studentService.selectAllStudentByClassNo(classNo);
		PageInfo pageInfo = new PageInfo(currentPage, studentList.size());
		model.addAttribute("studentList", studentList.subList(pageInfo.getStartRow() - 1, pageInfo.getEndRow()));
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("classNo", classNo);
		return "/admin/studentList";
	}

	@GetMapping("/addStudentForm")
	public String addStudentForm(Model model) {
		model.addAttribute("classList", studentService.getAllClass());
		return "/admin/addStudent";
	}

	@PostMapping("/addStudent")
	public String addStudent(Model model, User user, @RequestParam("file") MultipartFile file) {
		studentService.addStudent(user, file);
		model.addAttribute("url", "/admin/studentList?classNo=" + user.getClassNo());
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
		model.addAttribute("url", "/admin/studentList?classNo=" + classNo);
		return ForwardPageType.FORWARD_GOPAGE.getForwardPage();
		
	}

	@GetMapping("/modifyStudentForm")
	public String modifyStudentForm(Model model, @RequestParam("id") String id) {
		model.addAttribute("studentInfo", studentService.selectStudentById(id));
		model.addAttribute("classList", studentService.getAllClass());
		return "/admin/modifyStudent";
	}

	@PostMapping("/modifyStudent")
	public String modifyStudent(Model model, User user, @RequestParam("file") MultipartFile file) {
		studentService.updateStudent(user, file);
		model.addAttribute("url", "/admin/studentList?classNo=" + user.getClassNo());
		try {
			model.addAttribute("msg", propertyConfigurer.getObject().getProperty("success.admin.update"));
		} catch (IOException e) {
			logger.error("message key error!");
		}
		return ForwardPageType.FORWARD_GOPAGE.getForwardPage();
	}
}
