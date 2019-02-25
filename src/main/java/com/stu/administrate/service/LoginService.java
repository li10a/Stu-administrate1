package com.stu.administrate.service;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.stu.administrate.model.User;
import com.stu.administrate.repository.UserRepository;
import com.stu.administrate.type.UserType;

@Service
public class LoginService {

	private Logger logger = LoggerFactory.getLogger(LoginService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	@Qualifier("propertyConfigurer")
	private PropertiesFactoryBean propertyConfigurer;

	public boolean doLogin(ModelAndView modelAndView, HttpServletRequest request, String id, String password, String type) {
		try {
			User user = null;
			if (UserType.ADMIN.getType().equals(type) || UserType.TEACHER.getType().equals(type)) {
				user = userRepository.selectAdminUser(id, type);
			} else {
				user = userRepository.selectStudent(id);
			}

			if (user == null) {
				modelAndView.addObject("errorMsg", propertyConfigurer.getObject().getProperty("error.login.user.notexsit"));
				return false;
			}

			if (!user.getPassword().equals(password)) {
				modelAndView.addObject("errorMsg", propertyConfigurer.getObject().getProperty("error.login.user.password.wrong"));
				return false;
			}
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}

		return true;
	}
}
