package com.stu.administrate.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.stu.administrate.model.Class;
import com.stu.administrate.model.User;

@Repository
public interface AdminRepository {

	User selectAdminUserById(@Param("id") String id);

	User selectStudentById(@Param("id") String id);

	void insertTeacherUser(@Param("id") String id, @Param("name") String name, @Param("password") String password);

	void insertClass(@Param("name") String name, @Param("teacherId") String teacherId, @Param("slogan") String slogan);

	List<User> selectAllTeacher();

	List<Class> selectAllClass();

	String selectTeacherNameById(@Param("id") String id);

	void deleteClassByNo(@Param("no") int no);
}
