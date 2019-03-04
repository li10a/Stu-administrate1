package com.stu.administrate.service;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stu.administrate.exception.LoginException;
import com.stu.administrate.model.User;
import com.stu.administrate.repository.AdminRepository;

@Service
public class LoginService {

	private Logger logger = LoggerFactory.getLogger(LoginService.class);

	@Autowired
	private AdminRepository adminRepository;

	public User doLogin(HttpServletRequest request, String id, String password) throws LoginException {
		try {
			User user = adminRepository.selectAdminUserById(id);
			
			if (user == null) {
				user = adminRepository.selectStudentById(id);
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
}
