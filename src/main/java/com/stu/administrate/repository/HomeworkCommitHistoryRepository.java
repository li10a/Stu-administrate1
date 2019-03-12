package com.stu.administrate.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.stu.administrate.model.HomeworkCommitHistory;

@Repository
public interface HomeworkCommitHistoryRepository {

	List<HomeworkCommitHistory> selectAllHomeworkCommitHistory();
	
	void insertHomeworkCommitHistory(@Param("homeworkNo") int homeworkNo, @Param("studentNo") int studentNo, @Param("homeworkPath") String homeworkPath);

	HomeworkCommitHistory selectHomeworkCommitHistoryByHomeworkNoAndStudentNo(@Param("homeworkNo") int homeworkNo, @Param("studentNo") int studentNo);

	void updateHomeworkCommitHistoryHomeworkPathByHomeworkNo(@Param("homeworkNo") int homeworkNo, @Param("homeworkPath") String homeworkPath);

	List<HomeworkCommitHistory> selectHomeworkCommitHistoryByHomeworkNo(@Param("homeworkNo") int homeworkNo);
}
