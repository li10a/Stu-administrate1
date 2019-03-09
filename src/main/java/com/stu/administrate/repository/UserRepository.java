package com.stu.administrate.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.stu.administrate.model.User;

@Repository
public interface UserRepository {

	User selectAdminUserById(@Param("id") String id);

	User selectStudentById(@Param("id") String id);

	void insertTeacherUser(@Param("id") String id, @Param("name") String name, @Param("password") String password);

	List<User> selectAllTeacher();

	String selectTeacherNameById(@Param("id") String id);

	void deleteTeacherByNo(@Param("no") int no);

	void modifyTeacher(@Param("no") int no, @Param("id") String id, @Param("name") String name, @Param("password") String password);

	User selectTeacherByNo(@Param("no") int no);

	void insertStudent(User user);

	List<User> selectAllStudentByClassNo(@Param("classNo") int classNo);

	void deleteStudentById(@Param("id") String id);
	
	void updateStudent(User user);
}
