package com.stu.administrate.service;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.stu.administrate.constants.ServerFolderConstant;
import com.stu.administrate.model.Class;
import com.stu.administrate.model.Homework;
import com.stu.administrate.model.HomeworkCommitHistory;
import com.stu.administrate.model.User;
import com.stu.administrate.repository.ClassRepository;
import com.stu.administrate.repository.HomeworkCommitHistoryRepository;
import com.stu.administrate.repository.HomeworkRepository;

@Service
public class HomeworkCommitHistoryService {

	@Autowired
	private ClassRepository classRepository;

	@Autowired
	private HomeworkRepository homeworkRepository;

	@Autowired
	private HomeworkCommitHistoryRepository homeworkCommitHistoryRepository;

	public List<HomeworkCommitHistory> selectAllHomeworkCommitHistory() {
		List<HomeworkCommitHistory> homeworkCommitHistoryList = homeworkCommitHistoryRepository
				.selectAllHomeworkCommitHistory();
		Iterator<HomeworkCommitHistory> iterator = homeworkCommitHistoryList.iterator();
		while (iterator.hasNext()) {
			HomeworkCommitHistory homeworkCommitHistory = (HomeworkCommitHistory) iterator.next();
			if (homeworkCommitHistory.getScore() == 0) {
				iterator.remove();
			}
			
		}
		for (HomeworkCommitHistory homeworkCommitHistory : homeworkCommitHistoryList) {
			homeworkCommitHistory.setHomeworkTitle(homeworkRepository.selectHomeworkByNo(homeworkCommitHistory.getNo()).getTitle());
		}
		return homeworkCommitHistoryList;
	}

	public void uploadHomework(int homeworkNo, MultipartFile file, User loginUser) {
		if (file.getOriginalFilename() != null && file.getOriginalFilename() != "") {
			Class class1 = classRepository.selectClassByNo(loginUser.getClassNo());
			Homework homework = homeworkRepository.selectHomeworkByNo(homeworkNo);
			String path = ServerFolderConstant.FOLDER_PATH + "\\" + class1.getName() + "\\homework\\" + homework.getTitle() + "\\" +loginUser.getId() + "_" + file.getOriginalFilename();
			try {
				file.transferTo(new File(path));
			} catch (Exception e) {
				System.out.println(e.getMessage());
				// do nothing
			}
			HomeworkCommitHistory history = homeworkCommitHistoryRepository.selectHomeworkCommitHistoryByHomeworkNoAndStudentNo(homeworkNo, loginUser.getNo());
			if (history != null) {
				homeworkCommitHistoryRepository.updateHomeworkCommitHistoryHomeworkPathByHomeworkNo(homeworkNo, path);
			} else {
				homeworkCommitHistoryRepository.insertHomeworkCommitHistory(homeworkNo, loginUser.getNo(), path);
			}
		}
	}

	public void updateScore(int homeworkCommitHistoryNo, int score) {
		homeworkCommitHistoryRepository.updateScore(homeworkCommitHistoryNo, score);
	}
}
