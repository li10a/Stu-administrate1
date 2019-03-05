package com.stu.administrate.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public void modifyClass(int no, String name, List<String> teacherIdList, String slogan) {
		adminRepository.modifyClass(no, name, this.formatTeacherID(teacherIdList), slogan);
	}

	public void deleteClassByNo(int no) {
		adminRepository.deleteClassByNo(no);
	}

	public Class selectClassByNo(int no) {
		return adminRepository.selectClassByNo(no);
	}
	
	public List<User> selectAllTeacher() {
		return adminRepository.selectAllTeacher();
	}

	public void deleteTeacherByNo(int no) {
		adminRepository.deleteTeacherByNo(no);
	}

	public Map<String, Boolean> getClassTeacherIdMap(String techerIds) {
		Map<String, Boolean> classTeacherIdMap = new HashMap<String, Boolean>();
		String[] teacherIds = techerIds.split("@");
		for (String id : teacherIds) {
			classTeacherIdMap.put(id, true);
		}
		return classTeacherIdMap;
	}

	private String formatTeacherID(List<String> teacherId) {
		return String.join("@", teacherId);
	}

	public List<Class> selectAllClass() {
		List<Class> classList = adminRepository.selectAllClass();
		for (Class class1 : classList) {
			// TODO
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

	public Map<String, String> getTeacherClasses(List<User> teacherList) {
		Map<String, String> teacherClasses = new HashMap<String, String>();
		for (User teacher : teacherList) {
			List<Class> classList = adminRepository.selectClassByTeacherId(teacher.getId());
			StringBuffer displayClassesName = new StringBuffer();
			for (int i = 0; i < classList.size(); i++) {
				displayClassesName.append(classList.get(i).getName());
				if (i < classList.size() - 1) {
					displayClassesName.append(",");
				}
			}
			teacherClasses.put(teacher.getId(), displayClassesName.toString());
		}
		return teacherClasses;
	}

	public void modifyTeacher(int no, String id, String name, String password) {
		adminRepository.modifyTeacher(no, id, name, password);
	}

	public User selectTeacherByNo(int no) {
		return adminRepository.selectTeacherByNo(no);
	}
}
