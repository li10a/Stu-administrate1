package com.stu.administrate.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.stu.administrate.model.HomeworkCommitHistory;

@Repository
public interface HomeworkCommitHistoryRepository {

	List<HomeworkCommitHistory> selectAllHomeworkCommitHistory();
	
	void insertHomeworkCommitHistory(int homeworkNo, int studentNo, String homework_path);
}
