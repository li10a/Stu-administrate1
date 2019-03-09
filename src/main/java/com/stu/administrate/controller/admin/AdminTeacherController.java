package com.stu.administrate.controller.admin;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.stu.administrate.model.User;
import com.stu.administrate.service.TeacherService;
import com.stu.administrate.type.ForwardPageType;
import com.stu.administrate.util.PageInfo;

@Controller
@RequestMapping("/admin")
public class AdminTeacherController {

	private Logger logger = LoggerFactory.getLogger(AdminTeacherController.class);

	@Autowired
	TeacherService teacherService;

	@Autowired
	@Qualifier("propertyConfigurer")
	private PropertiesFactoryBean propertyConfigurer;

	@GetMapping("/teacherList")
	public String getTeacherList(Model model, @RequestParam(name="page", defaultValue="1") int currentPage) {
		List<User> teacherList = teacherService.selectAllTeacher();
		PageInfo pageInfo = new PageInfo(currentPage, teacherList.size());

		model.addAttribute("teacherList", teacherList.subList(pageInfo.getStartRow() - 1, pageInfo.getEndRow()));
		model.addAttribute("teacherClasses", teacherService.getTeacherClasses(teacherList));
		model.addAttribute("pageInfo", pageInfo);
		return "/admin/teacherList";
	}

	@GetMapping("/addTeacherForm")
	public String addTeacherForm() {
		return "/admin/addTeacher";
	}

	@PostMapping("/addTeacher")
	public ModelAndView addTeacher(@RequestParam(value = "name", required = true) String name, @RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "password", required = true) String password) {
		ModelAndView mav = new ModelAndView();
		teacherService.insertTeacherUser(id, name, password);
		mav.addObject("url", "/admin/teacherList");
		try {
			mav.addObject("msg", propertyConfigurer.getObject().getProperty("success.admin.add"));
		} catch (IOException e) {
			logger.error("message key error!");
		}
		mav.setViewName(ForwardPageType.FORWARD_GOPAGE.getForwardPage());
		return mav;
	}

	@GetMapping("/modifyTeacherForm")
	public String modifyTeacherForm(Model model, @RequestParam("no") int no) {
		model.addAttribute("teacherInfo", teacherService.selectTeacherByNo(no));
		return "/admin/modifyTeacher";
	}

	@PostMapping("/modifyTeacher")
	public ModelAndView modifyTeacher(@RequestParam("no") int no, @RequestParam(value = "name", required = true) String name, @RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "password", required = true) String password) {
		ModelAndView mav = new ModelAndView();
		teacherService.modifyTeacher(no, id, name,  password);
		mav.addObject("url", "/admin/teacherList");
		try {
			mav.addObject("msg", propertyConfigurer.getObject().getProperty("success.admin.modify"));
		} catch (IOException e) {
			logger.error("message key error!");
		}
		mav.setViewName(ForwardPageType.FORWARD_GOPAGE.getForwardPage());
		return mav;
	}

	@GetMapping("/deleteTeacher")
	public ModelAndView deleteTeacher(@RequestParam("no") int no){
		ModelAndView mav = new ModelAndView();
		teacherService.deleteTeacherByNo(no);
		try {
			mav.addObject("msg", propertyConfigurer.getObject().getProperty("success.admin.delete"));
		} catch (IOException e) {
			logger.error("message key error!");
		}
		mav.addObject("url", "/admin/teacherList");
		mav.setViewName(ForwardPageType.FORWARD_GOPAGE.getForwardPage());
		return mav;
	}
}
