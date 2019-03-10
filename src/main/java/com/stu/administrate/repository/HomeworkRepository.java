package com.stu.administrate.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.stu.administrate.model.Homework;

@Repository
public interface HomeworkRepository {

	void insertHomework(@Param("title") String title, @Param("task") String task, @Param("classNo") int classNo, @Param("teacherId") String teacherId);

	List<Homework> selectAllHomeworkByTeacher(@Param("teacherId") String teacherId);

	Homework selectHomeworkByNo(@Param("no") int no);

	void updateHomeworkByNo(@Param("no") int no, @Param("title") String title, @Param("task") String task);

	void deleteHomeworkByNo(@Param("no") int no);
}
