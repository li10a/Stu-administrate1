package com.stu.administrate.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stu.administrate.model.HomeworkCommitHistory;
import com.stu.administrate.repository.HomeworkCommitHistoryRepository;

@Service
public class HomeworkCommitHistoryService {

	@Autowired
	HomeworkCommitHistoryRepository homeworkCommitHistoryRepository;

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
		return homeworkCommitHistoryList;
	}
}
