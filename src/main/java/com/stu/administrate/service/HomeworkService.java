package com.stu.administrate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stu.administrate.model.Class;
import com.stu.administrate.model.Homework;
import com.stu.administrate.model.HomeworkCommitHistory;
import com.stu.administrate.model.User;
import com.stu.administrate.repository.ClassRepository;
import com.stu.administrate.repository.HomeworkCommitHistoryRepository;
import com.stu.administrate.repository.HomeworkRepository;
import com.stu.administrate.repository.UserRepository;

@Service
public class HomeworkService {

	@Autowired
	private HomeworkRepository homeworkRepository;

	@Autowired
	private ClassRepository classRepository;

	@Autowired
	private HomeworkCommitHistoryRepository homeworkCommitHistoryRepository;
	
	@Autowired
	private UserRepository userRepository;

	public void insertHomework(String title, String task, int classNo, String teacherId) {
		homeworkRepository.insertHomework(title, task, classNo, teacherId);
	}

	public List<Homework> selectAllHomework(String teacherId) {
		List<Homework> homeworkList = homeworkRepository.selectAllHomeworkByTeacher(teacherId);
		for (Homework homework : homeworkList) {
			Class class1 = classRepository.selectClassByNo(homework.getClassNo());
			homework.setClassName(class1.getName());
			
			homework.setCommitStudentCnt(homeworkCommitHistoryRepository.selectHomeworkCommitHistoryByHomeworkNo(homework.getNo()).size());
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
	
	public List<Homework> selectAllHomeworkByClassNoDesc(int classNo) {
		List<Homework> homeworkList = homeworkRepository.selectAllHomeworkByClassNoDesc(classNo);
		for (Homework homework : homeworkList) {
			if (homeworkCommitHistoryRepository.selectHomeworkCommitHistoryByHomeworkNo(homework.getNo()) != null) {
				homework.setCommited(true);
			}
		}
		return homeworkList;
	}
	
	public List<HomeworkCommitHistory> selectHomeworkCommitHistoryByHomeworkNo(int homeworkNo) {
		List<HomeworkCommitHistory> historyList = homeworkCommitHistoryRepository.selectHomeworkCommitHistoryByHomeworkNo(homeworkNo);
		for (HomeworkCommitHistory homeworkCommitHistory : historyList) {
			User student = userRepository.selectStudentByNo(homeworkCommitHistory.getStudentNo());
			homeworkCommitHistory.setStudentName(student.getName());
		}
		return historyList;
	}
}
