package com.stu.administrate.service;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stu.administrate.exception.LoginException;
import com.stu.administrate.model.User;
import com.stu.administrate.repository.UserRepository;
import com.stu.administrate.util.CookieUtil;

@Service
public class LoginService {

	private Logger logger = LoggerFactory.getLogger(LoginService.class);

	@Autowired
	private UserRepository userRepository;

	public User doLogin(String id, String password) throws LoginException {
		try {
			User user = userRepository.selectAdminUserById(id);
			
			if (user == null) {
				user = userRepository.selectStudentById(id);
			}

			if (user == null) {
				throw new LoginException("error.login.user.notexsit");
			}

			if (!user.getPassword().equals(password)) {
				throw new LoginException("error.login.user.password.wrong");
			}
			
			return user;
		} catch (LoginException e) {
			logger.debug(e.getMessage());
			throw e;
		}

	}

	public void setCookie(User user, HttpServletResponse response) {
		String cookieValue = user.getNo() + "&" + user.getId() + "&" + user.getName() + "&" + user.getType();
		CookieUtil.setCookie(response, "user", cookieValue);
	}

	public void clearLoginCookie(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = CookieUtil.getCookie(request, "user");
		if (cookie != null) {
			Cookie newCookie = new Cookie("user", null);
			newCookie.setMaxAge(0);
			newCookie.setPath("/");
			response.addCookie(newCookie);
		}
	}
}
