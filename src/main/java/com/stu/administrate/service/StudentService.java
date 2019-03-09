package com.stu.administrate.service;

import java.io.File;
import java.util.List;

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
			if (file.getOriginalFilename() != null && file.getOriginalFilename() != "") {
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
	
	public void deleteStudentById(String id) {
		User deleteStudent = userRepository.selectStudentById(id);
		if (deleteStudent != null) {
			userRepository.deleteStudentById(deleteStudent.getId());
			if (deleteStudent.getImage() != null && deleteStudent.getImage() != "") {
				File file = new File(deleteStudent.getImage());
				file.delete();
			}
		}
	}
	
	public User selectStudentById(String id) {
		User student = userRepository.selectStudentById(id);
		if (student.getImage() != null && student.getId() != "") {
			student.setImage(student.getImage().replace(ServerFolderConstant.FOLDER_PATH, "/pic"));
		}
		return student;
	}

	public void updateStudent(User user, MultipartFile file) {
		try {
			String path = "";
			if (file.getOriginalFilename() != null && file.getOriginalFilename() != "") {
				Class class1 = classRepository.selectClassByNo(user.getClassNo());
				path = ServerFolderConstant.FOLDER_PATH + "\\" + class1.getName() + "\\image\\" + user.getId() + "_" + file.getOriginalFilename();
				file.transferTo(new File(path));
				user.setImage(path);
			} else {
				User user1 = userRepository.selectStudentById(user.getId());
				user.setImage(user1.getImage());
			}
			
			userRepository.updateStudent(user);
		} catch (Exception e) {
		}
	}
}
