package com.stu.administrate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stu.administrate.model.Class;
import com.stu.administrate.model.Homework;
import com.stu.administrate.repository.ClassRepository;
import com.stu.administrate.repository.HomeworkRepository;

@Service
public class HomeworkService {

	@Autowired
	private HomeworkRepository homeworkRepository;

	@Autowired
	private ClassRepository classRepository;

	public void insertHomework(String title, String task, int classNo, String teacherId) {
		homeworkRepository.insertHomework(title, task, classNo, teacherId);
	}

	public List<Homework> selectAllHomework(String teacherId) {
		List<Homework> homeworkList = homeworkRepository.selectAllHomeworkByTeacher(teacherId);
		for (Homework homework : homeworkList) {
			Class class1 = classRepository.selectClassByNo(homework.getClassNo());
			homework.setClassName(class1.getName());
			
			// TODO
			homework.setCommitStudentCnt(12);
		}
		return homeworkList;
	}

	public Homework selectHomeworkByNo(int no) {
		return homeworkRepository.selectHomeworkByNo(no);
	}

	public void updateHomeworkByNo(int no, String title, String task) {
		homeworkRepository.updateHomeworkByNo(no, title, task);
	}
	
	public void deleteHomeworkByNo(int no) {
		homeworkRepository.deleteHomeworkByNo(no);
	}
}
