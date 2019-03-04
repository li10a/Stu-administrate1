package com.stu.administrate.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stu.administrate.model.Class;
import com.stu.administrate.model.User;
import com.stu.administrate.repository.AdminRepository;

@Service
public class AdminService {

	private Logger logger = LoggerFactory.getLogger(AdminService.class);

	@Autowired
	private AdminRepository adminRepository;

	public void insertTeacherUser(String id, String name, String password) {
		adminRepository.insertTeacherUser(id, name, password);
	}

	public void insertClass(String name, List<String> teacherId, String slogan) {
		adminRepository.insertClass(name, this.formatTeacherID(teacherId), slogan);
	}

	public void deleteClassByNo(int no) {
		adminRepository.deleteClassByNo(no);
	}

	public List<User> selectAllTeacher() {
		return adminRepository.selectAllTeacher();
	}

	private String formatTeacherID(List<String> teacherId) {
		return String.join("@", teacherId);
	}

	public List<Class> selectAllClass() {
		List<Class> classList = adminRepository.selectAllClass();
		for (Class class1 : classList) {
			class1.setStudentCnt(32);

			String[] teacherIds = class1.getTeacherId().split("@");
			StringBuffer displayTeacherName = new StringBuffer();
			for (int i = 0; i < teacherIds.length; i++) {
				displayTeacherName.append(adminRepository.selectTeacherNameById(teacherIds[i]));
				if (i < teacherIds.length - 1) {
					displayTeacherName.append(",");
				}
			}
			class1.setTeacherId(displayTeacherName.toString());
		}
		return classList;
	}
}
