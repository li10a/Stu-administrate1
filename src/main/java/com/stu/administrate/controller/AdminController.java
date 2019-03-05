package com.stu.administrate.controller;

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

import com.stu.administrate.model.Class;
import com.stu.administrate.model.User;
import com.stu.administrate.service.AdminService;
import com.stu.administrate.type.ForwardPageType;
import com.stu.administrate.util.PageInfo;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	AdminService adminService;

	@Autowired
	@Qualifier("propertyConfigurer")
	private PropertiesFactoryBean propertyConfigurer;

	@GetMapping("/classList")
	public String getClassList(Model model, @RequestParam(name="page", defaultValue="1") int currentPage) {
		List<Class> classList = adminService.selectAllClass();
		PageInfo pageInfo = new PageInfo(currentPage, classList.size());
		model.addAttribute("classList", classList.subList(pageInfo.getStartRow() - 1, pageInfo.getEndRow()));
		model.addAttribute("pageInfo", pageInfo);
		return "/admin/superadmin/classList";
	}

	@GetMapping("/addClassForm")
	public String addClassForm(Model model) {
		model.addAttribute("teacherList", adminService.selectAllTeacher());
		return "/admin/superadmin/addClass";
	}

	@PostMapping("/addClass")
	public ModelAndView addClass(@RequestParam("name") String name, @RequestParam("teacherIdList") List<String> teacherIdList, @RequestParam("slogan") String slogan) {
		ModelAndView mav = new ModelAndView();
		adminService.insertClass(name, teacherIdList, slogan);
		mav.addObject("url", "/admin/classList");
		try {
			mav.addObject("msg", propertyConfigurer.getObject().getProperty("success.admin.add"));
		} catch (IOException e) {
			logger.error("message key error!");
		}
		mav.setViewName(ForwardPageType.FORWARD_GOPAGE.getForwardPage());
		return mav;
	}

	@GetMapping("/modifyClassForm")
	public String modifyClassForm(Model model, @RequestParam("no") int no) {
		model.addAttribute("teacherList", adminService.selectAllTeacher());
		Class class1 = adminService.selectClassByNo(no);
		model.addAttribute("classInfo", class1);
		model.addAttribute("classTeacherIdMap", adminService.getClassTeacherIdMap(class1.getTeacherId()));
		return "/admin/superadmin/modifyClass";
	}

	@PostMapping("/modifyClass")
	public ModelAndView modifyClass(@RequestParam("no") int no, @RequestParam("name") String name, @RequestParam("teacherIdList") List<String> teacherIdList, @RequestParam("slogan") String slogan) {
		ModelAndView mav = new ModelAndView();
		adminService.modifyClass(no, name, teacherIdList, slogan);
		mav.addObject("url", "/admin/classList");
		try {
			mav.addObject("msg", propertyConfigurer.getObject().getProperty("success.admin.modify"));
		} catch (IOException e) {
			logger.error("message key error!");
		}
		mav.setViewName(ForwardPageType.FORWARD_GOPAGE.getForwardPage());
		return mav;
	}

	@GetMapping("/deleteClass")
	public ModelAndView deleteClass(@RequestParam("no") int no){
		ModelAndView mav = new ModelAndView();
		adminService.deleteClassByNo(no);
		try {
			mav.addObject("msg", propertyConfigurer.getObject().getProperty("success.admin.delete"));
		} catch (IOException e) {
			logger.error("message key error!");
		}
		mav.addObject("url", "/admin/classList");
		mav.setViewName(ForwardPageType.FORWARD_GOPAGE.getForwardPage());
		return mav;
	}

	@GetMapping("/deleteTeacher")
	public ModelAndView deleteTeacher(@RequestParam("no") int no){
		ModelAndView mav = new ModelAndView();
		adminService.deleteTeacherByNo(no);
		try {
			mav.addObject("msg", propertyConfigurer.getObject().getProperty("success.admin.delete"));
		} catch (IOException e) {
			logger.error("message key error!");
		}
		mav.addObject("url", "/admin/teacherList");
		mav.setViewName(ForwardPageType.FORWARD_GOPAGE.getForwardPage());
		return mav;
	}

	@GetMapping("/teacherList")
	public String getTeacherList(Model model, @RequestParam(name="page", defaultValue="1") int currentPage) {
		List<User> teacherList = adminService.selectAllTeacher();
		PageInfo pageInfo = new PageInfo(currentPage, teacherList.size());

		model.addAttribute("teacherList", teacherList.subList(pageInfo.getStartRow() - 1, pageInfo.getEndRow()));
		model.addAttribute("teacherClasses", adminService.getTeacherClasses(teacherList));
		model.addAttribute("pageInfo", pageInfo);
		return "/admin/superadmin/teacherList";
	}

	@GetMapping("/addTeacherForm")
	public String addTeacherForm() {
		return "/admin/superadmin/addTeacher";
	}

	@PostMapping("/addTeacher")
	public String addTeacher(@RequestParam(value = "name", required = true) String name, @RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "password", required = true) String password) {
		adminService.insertTeacherUser(id, name, password);
		return "/admin/superadmin/teacherList";
	}

	@GetMapping("/modifyTeacherForm")
	public String modifyTeacherForm(Model model, @RequestParam("no") int no) {
		model.addAttribute("teacherInfo", adminService.selectTeacherByNo(no));
		return "/admin/superadmin/modifyTeacher";
	}

	@PostMapping("/modifyTeacher")
	public ModelAndView modifyTeacher(@RequestParam("no") int no, @RequestParam(value = "name", required = true) String name, @RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "password", required = true) String password) {
		ModelAndView mav = new ModelAndView();
		adminService.modifyTeacher(no, id, name,  password);
		mav.addObject("url", "/admin/teacherList");
		try {
			mav.addObject("msg", propertyConfigurer.getObject().getProperty("success.admin.modify"));
		} catch (IOException e) {
			logger.error("message key error!");
		}
		mav.setViewName(ForwardPageType.FORWARD_GOPAGE.getForwardPage());
		return mav;
	}
}
