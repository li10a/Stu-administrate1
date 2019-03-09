package com.stu.administrate.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.stu.administrate.exception.LoginException;
import com.stu.administrate.model.User;
import com.stu.administrate.service.LoginService;
import com.stu.administrate.type.ForwardPageType;
import com.stu.administrate.type.UserType;

@Controller
public class LoginController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private LoginService loginService;

	@Autowired
	@Qualifier("propertyConfigurer")
	private PropertiesFactoryBean propertyConfigurer;

	@GetMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		loginService.clearLoginCookie(request, response);
		return "/login";
	}

	@PostMapping("/doLogin")
	public ModelAndView doAdminLogin(HttpServletResponse response, @RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "password", required = true) String password) {
		ModelAndView mav = new ModelAndView();
		try {
			User user = loginService.doLogin(id, password);

			if (UserType.ADMIN.getType().equals(user.getType())) {
				mav.addObject("url", "/admin/classList");
				mav.setViewName(ForwardPageType.FORWARD_GOPAGE.getForwardPage());
			} else if (UserType.TEACHER.getType().equals(user.getType())) {
				mav.addObject("url", "/teacher/classList");
				mav.setViewName(ForwardPageType.FORWARD_GOPAGE.getForwardPage());
			} else if (UserType.STUDENT.getType().equals(user.getType())) {
				mav.addObject("url", "/student/overview");
				mav.setViewName(ForwardPageType.FORWARD_GOPAGE.getForwardPage());
			}
			// set cookie
			loginService.setCookie(user, response);
			return mav;
		} catch (LoginException e) {
			try {
				mav.addObject("errorMsg", propertyConfigurer.getObject().getProperty(e.getMsgKey()));
			} catch (IOException e1) {
				logger.error(e1.getMessage());
			}
			mav.setViewName(ForwardPageType.FORWARD_ALERT_MSG.getForwardPage());
			return mav;
		}
	}
}
