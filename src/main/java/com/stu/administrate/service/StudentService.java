package com.stu.administrate.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.stu.administrate.constants.ServerFolderConstant;
import com.stu.administrate.model.Class;
import com.stu.administrate.model.User;
import com.stu.administrate.repository.ClassRepository;
import com.stu.administrate.repository.UserRepository;

@Service
public class StudentService {

	@Autowired
	ClassRepository classRepository;

	@Autowired
	UserRepository userRepository;

	public List<Class> getAllClass() {
		return classRepository.selectAllClass();
	}

	public void addStudent(User user, MultipartFile file) {
		try {
			String path = "";
			if (file != null) {
				Class class1 = classRepository.selectClassByNo(user.getClassNo());
				path = ServerFolderConstant.FOLDER_PATH + "\\" + class1.getName() + "\\image\\" + user.getId() + "_" + file.getOriginalFilename();
				file.transferTo(new File(path));
			}
			user.setImage(path);
			userRepository.insertStudent(user);
		} catch (Exception e) {
		}
	}

	public int getMinClassNo() {
		return classRepository.selectMinClassNo();
	}

	public List<User> selectAllStudentByClassNo(int classNo) {
		return userRepository.selectAllStudentByClassNo(classNo);
	}
}
