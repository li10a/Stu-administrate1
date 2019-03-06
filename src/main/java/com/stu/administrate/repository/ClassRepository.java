package com.stu.administrate.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.stu.administrate.model.Class;

@Repository
public interface ClassRepository {

	void insertClass(@Param("name") String name, @Param("teacherId") String teacherId, @Param("slogan") String slogan);

	List<Class> selectAllClass();

	List<Class> selectClassByTeacherId(@Param("teacherId") String teacherId);

	void deleteClassByNo(@Param("no") int no);

	Class selectClassByNo(@Param("no") int no);

	void modifyClass(@Param("no") int no, @Param("name") String name, @Param("teacherId") String teacherId, @Param("slogan") String slogan);
}
