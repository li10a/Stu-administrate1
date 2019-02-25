package com.stu.administrate.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.cj.util.TestUtils;
import com.stu.administrate.service.LoginService;
import com.stu.administrate.type.ForwardPageType;
import com.stu.administrate.type.UserType;

@Controller
public class LoginController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private LoginService loginService;

	@GetMapping("/login")
	public String hello() {
		return "/login";
	}

	@PostMapping("/doLogin")
	public ModelAndView doAdminLogin(HttpServletRequest request, @RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "type", required = true) String type) {
		ModelAndView modelAndView = new ModelAndView();
		boolean isAuth = loginService.doLogin(modelAndView, request, id, password, type);
		if (!isAuth) {
			modelAndView.setViewName(ForwardPageType.FORWARD_ALERT_MSG.getForwardPage());
			return modelAndView;
		}

		if (UserType.ADMIN.getType().equals(type)) {
			modelAndView.setViewName("/admin/index");
			return modelAndView;
		}
		return modelAndView;
	}
}
