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

import com.stu.administrate.model.Class;
import com.stu.administrate.model.User;
import com.stu.administrate.service.ClassService;
import com.stu.administrate.type.ForwardPageType;
import com.stu.administrate.util.PageInfo;

@Controller
@RequestMapping("/teacher")
public class TeacherClassController {

	private Logger logger = LoggerFactory.getLogger(TeacherClassController.class);

	@Autowired
	private ClassService classService;

	@Autowired
	@Qualifier("propertyConfigurer")
	private PropertiesFactoryBean propertyConfigurer;

	@GetMapping("/classList")
	public String getClassList(Model model, @RequestParam(name="page", defaultValue="1") int currentPage, @RequestAttribute("loginUser") User loginUser) {
		List<Class> classList = classService.selectClassByTeacherId(loginUser.getId());

		PageInfo pageInfo = new PageInfo(currentPage, classList.size());

		List<Class> disClassList = classList.subList(pageInfo.getStartRow() - 1, pageInfo.getEndRow());
		classService.setClassStudentCnt(disClassList);

		model.addAttribute("classList", disClassList);
		model.addAttribute("pageInfo", pageInfo);
		return "/teacher/classList";
	}

	@GetMapping("/modifyClassSloganForm")
	public String modifyClassSloganForm(Model model, @RequestParam("no") int no) {
		Class class1 = classService.selectClassByNo(no);
		model.addAttribute("classInfo", class1);
		return "/teacher/modifyClassSloganForm";
	}

	@PostMapping("/modifyClassSlogan")
	public String modifyClassSlogan(Model model, @RequestParam("no") int no, @RequestParam("slogan") String slogan) {
		classService.updateClassSloganByNo(no, slogan);
		model.addAttribute("url", "/teacher/classList");
		try {
			model.addAttribute("msg", propertyConfigurer.getObject().getProperty("success.admin.modify"));
		} catch (IOException e) {
			logger.error("message key error!");
		}
		return ForwardPageType.FORWARD_GOPAGE.getForwardPage();
	}
}
