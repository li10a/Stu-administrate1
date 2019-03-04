package com.stu.administrate.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.stu.administrate.model.Class;
import com.stu.administrate.service.AdminService;
import com.stu.administrate.type.ForwardPageType;
import com.stu.administrate.util.PageInfo;

@Controller
public class AdminController {

	@Autowired
	AdminService adminService;

	@Autowired
	@Qualifier("propertyConfigurer")
	private PropertiesFactoryBean propertyConfigurer;

	@GetMapping("/admin/classList")
	public String getClassList(Model model, @RequestParam(name="page", defaultValue="1") int currentPage) {
		List<Class> classList = adminService.selectAllClass();
		PageInfo pageInfo = new PageInfo(currentPage, classList.size());
		model.addAttribute("classList", classList.subList(pageInfo.getStartRow() - 1, pageInfo.getEndRow()));
		model.addAttribute("pageInfo", pageInfo);
		return "/admin/superadmin/classList";
	}

	@GetMapping("/admin/addClassForm")
	public String addClassForm(Model model) {
		model.addAttribute("teacherList", adminService.selectAllTeacher());
		return "/admin/superadmin/addClass";
	}

	@PostMapping("/admin/addClass")
	public ModelAndView addClass(@RequestParam("name") String name, @RequestParam("teacherIdList") List<String> teacherIdList, @RequestParam("slogan") String slogan) {
		ModelAndView mav = new ModelAndView();
		adminService.insertClass(name, teacherIdList, slogan);
		mav.addObject("url", "/admin/classList");
		mav.setViewName(ForwardPageType.FORWARD_GOPAGE.getForwardPage());
		return mav;
	}

	@GetMapping("/admin/deleteClass")
	public ModelAndView deleteClass(@RequestParam(value = "no") int no){
		ModelAndView mav = new ModelAndView();
		adminService.deleteClassByNo(no);
		try {
			mav.addObject("msg", propertyConfigurer.getObject().getProperty("success.admin.delete"));
		} catch (IOException e) {
			// do nothing
		}
		mav.addObject("url", "/admin/classList");
		mav.setViewName(ForwardPageType.FORWARD_GOPAGE.getForwardPage());
		return mav;
	}

	@GetMapping("/admin/addTeacherForm")
	public String addTeacherForm() {
		return "/admin/superadmin/addTeacher";
	}

	@PostMapping("/admin/addTeacher")
	public String addTeacher(@RequestParam(value = "name", required = true) String name, @RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "password", required = true) String password) {
		adminService.insertTeacherUser(id, name, password);
		return "/admin/superadmin/teacherList";
	}
}
