package com.stu.administrate.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.stu.administrate.model.User;

@Repository
public interface UserRepository {

	User selectAdminUser(@Param("id") String id, @Param("type") String type);

	User selectStudent(@Param("id") String id);

}
