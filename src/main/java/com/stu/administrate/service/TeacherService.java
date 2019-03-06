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
import com.stu.administrate.repository.ClassRepository;
import com.stu.administrate.repository.UserRepository;

@Service
public class TeacherService {

	private Logger logger = LoggerFactory.getLogger(TeacherService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ClassRepository classRepository;

	public void insertTeacherUser(String id, String name, String password) {
		userRepository.insertTeacherUser(id, name, password);
	}

	public List<User> selectAllTeacher() {
		return userRepository.selectAllTeacher();
	}

	public void deleteTeacherByNo(int no) {
		userRepository.deleteTeacherByNo(no);
	}

	public Map<String, Boolean> getClassTeacherIdMap(String techerIds) {
		Map<String, Boolean> classTeacherIdMap = new HashMap<String, Boolean>();
		String[] teacherIds = techerIds.split("@");
		for (String id : teacherIds) {
			classTeacherIdMap.put(id, true);
		}
		return classTeacherIdMap;
	}

	public Map<String, String> getTeacherClasses(List<User> teacherList) {
		Map<String, String> teacherClasses = new HashMap<String, String>();
		for (User teacher : teacherList) {
			List<Class> classList = classRepository.selectClassByTeacherId(teacher.getId());
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
		userRepository.modifyTeacher(no, id, name, password);
	}

	public User selectTeacherByNo(int no) {
		return userRepository.selectTeacherByNo(no);
	}
}
