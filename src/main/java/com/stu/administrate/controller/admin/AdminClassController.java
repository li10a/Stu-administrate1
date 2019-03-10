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

import com.stu.administrate.model.Class;
import com.stu.administrate.service.ClassService;
import com.stu.administrate.type.ForwardPageType;
import com.stu.administrate.util.PageInfo;

@Controller
@RequestMapping("/admin")
public class AdminClassController {

	private Logger logger = LoggerFactory.getLogger(AdminClassController.class);

	@Autowired
	private ClassService classService;

	@Autowired
	@Qualifier("propertyConfigurer")
	private PropertiesFactoryBean propertyConfigurer;

	@GetMapping("/classList")
	public String getClassList(Model model, @RequestParam(name="page", defaultValue="1") int currentPage) {
		List<Class> classList = classService.selectAllClass();
		PageInfo pageInfo = new PageInfo(currentPage, classList.size());
		List<Class> disClassList = classList.subList(pageInfo.getStartRow() - 1, pageInfo.getEndRow());
		classService.setClassStudentCnt(disClassList);
		model.addAttribute("classList", disClassList);
		model.addAttribute("pageInfo", pageInfo);
		return "/admin/classList";
	}

	@GetMapping("/addClassForm")
	public String addClassForm(Model model) {
		model.addAttribute("teacherList", classService.selectAllTeacher());
		return "/admin/addClass";
	}

	@PostMapping("/addClass")
	public ModelAndView addClass(@RequestParam("name") String name, @RequestParam("teacherIdList") List<String> teacherIdList, @RequestParam("slogan") String slogan) {
		ModelAndView mav = new ModelAndView();
		classService.insertClass(name, teacherIdList, slogan);
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
		model.addAttribute("teacherList", classService.selectAllTeacher());
		Class class1 = classService.selectClassByNo(no);
		model.addAttribute("classInfo", class1);
		model.addAttribute("classTeacherIdMap", classService.getClassTeacherIdMap(class1.getTeacherId()));
		return "/admin/modifyClass";
	}

	@PostMapping("/modifyClass")
	public ModelAndView modifyClass(@RequestParam("no") int no, @RequestParam("name") String name, @RequestParam("teacherIdList") List<String> teacherIdList, @RequestParam("slogan") String slogan) {
		ModelAndView mav = new ModelAndView();
		classService.modifyClass(no, name, teacherIdList, slogan);
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
		classService.deleteClassByNo(no);
		try {
			mav.addObject("msg", propertyConfigurer.getObject().getProperty("success.admin.delete"));
		} catch (IOException e) {
			logger.error("message key error!");
		}
		mav.addObject("url", "/admin/classList");
		mav.setViewName(ForwardPageType.FORWARD_GOPAGE.getForwardPage());
		return mav;
	}
}
