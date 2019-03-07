package com.stu.administrate.service;

import java.util.List;

import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stu.administrate.constants.ServerFolderConstant;
import com.stu.administrate.model.Class;
import com.stu.administrate.model.User;
import com.stu.administrate.repository.ClassRepository;

@Service
public class StudentService {

	@Autowired
	ClassRepository classRepository;

	public List<Class> getAllClass() {
		return classRepository.selectAllClass();
	}

	public void addStudent(User user, Part file) {
		String s = file.getHeader("Content-Disposition");
		s=s.substring(0,s.length()-1);
		String fileName=s.substring(s.lastIndexOf("\"")+1, s.length());

//		file.write(ServerFolderConstant.FOLDER_PATH + "\\" + user);
	}
}
