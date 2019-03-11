package com.stu.administrate.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.web.servlet.HandlerInterceptor;

import com.stu.administrate.model.User;
import com.stu.administrate.type.UserType;
import com.stu.administrate.util.CookieUtil;

public class TeacherLoginInterceptor implements HandlerInterceptor {

	@Autowired
	@Qualifier("propertyConfigurer")
	private PropertiesFactoryBean propertyConfigurer;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Cookie loginCookie = CookieUtil.getCookie(request, "user");
		if (loginCookie == null) {
			request.setAttribute("msg", propertyConfigurer.getObject().getProperty("error.not.login"));
			request.setAttribute("url", "/login");
			request.getRequestDispatcher("/WEB-INF/jsp/common/forward/gopage.jsp").forward(request, response);
			return false;
		}

		User user = this.getLoginUser(loginCookie);
		if (!UserType.ADMIN.getType().equals(user.getType()) && !UserType.TEACHER.getType().equals(user.getType())) {
			request.setAttribute("msg", propertyConfigurer.getObject().getProperty("error.login.user.permission"));
			request.setAttribute("url", "/login");
			request.getRequestDispatcher("/WEB-INF/jsp/common/forward/gopage.jsp").forward(request, response);
			return false;
		}

		request.setAttribute("loginUser", user);

		return true;
	}

	private User getLoginUser(Cookie cookie)  {
		String[] loginUserInfo = cookie.getValue().split("&");
		User loginUser = new User();
		loginUser.setNo(Integer.valueOf(loginUserInfo[0]));
		loginUser.setId(loginUserInfo[1]);
		loginUser.setName(loginUserInfo[2]);
		loginUser.setType(loginUserInfo[3]);
		loginUser.setClassNo(Integer.valueOf(loginUserInfo[4]));
		return loginUser;
	}

}
