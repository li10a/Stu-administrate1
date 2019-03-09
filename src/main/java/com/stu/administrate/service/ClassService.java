package com.stu.administrate.service;


import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stu.administrate.constants.ServerFolderConstant;
import com.stu.administrate.model.Class;
import com.stu.administrate.model.User;
import com.stu.administrate.repository.ClassRepository;
import com.stu.administrate.repository.UserRepository;

@Service
public class ClassService {

	private Logger logger = LoggerFactory.getLogger(ClassService.class);

	@Autowired
	private ClassRepository classRepository;

	@Autowired
	private UserRepository userRepository;

	public void insertClass(String name, List<String> teacherId, String slogan) {
		classRepository.insertClass(name, this.formatTeacherID(teacherId), slogan);
	}

	public void modifyClass(int no, String name, List<String> teacherIdList, String slogan) {
		classRepository.modifyClass(no, name, this.formatTeacherID(teacherIdList), slogan);
	}

	public void deleteClassByNo(int no) {
		classRepository.deleteClassByNo(no);
	}

	public Class selectClassByNo(int no) {
		return classRepository.selectClassByNo(no);
	}

	public List<User> selectAllTeacher() {
		return userRepository.selectAllTeacher();
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
		List<Class> classList = classRepository.selectAllClass();
		for (Class class1 : classList) {
			// TODO
			class1.setStudentCnt(32);

			String[] teacherIds = class1.getTeacherId().split("@");
			StringBuffer displayTeacherName = new StringBuffer();
			for (int i = 0; i < teacherIds.length; i++) {
				displayTeacherName.append(userRepository.selectTeacherNameById(teacherIds[i]));
				if (i < teacherIds.length - 1) {
					displayTeacherName.append(",");
				}
			}
			class1.setTeacherId(displayTeacherName.toString());
		}
		return classList;
	}

	public void makeDir(String className) {
		File file = new File(ServerFolderConstant.FOLDER_PATH + "\\" + className);
		if (!file.exists()) {
			file.mkdir();
		}
		File file1 = new File(ServerFolderConstant.FOLDER_PATH + "\\" + className + "\\image");
		if (!file1.exists()) {
			file1.mkdir();
		}
		File file2 = new File(ServerFolderConstant.FOLDER_PATH + "\\" + className + "\\homework");
		if (!file2.exists()) {
			file2.mkdir();
		}
	}

	public void setClassStudentCnt(List<Class> userList) {
		for (Class class1 : userList) {
			class1.setStudentCnt(userRepository.selectStudentCntByClassNo(class1.getNo()));
		}
	}

	public void updateClassSloganByNo(int no, String slogan) {
		classRepository.updateClassSloganByNo(no, slogan);
	}
}
