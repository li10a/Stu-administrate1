package com.stu.administrate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stu.administrate.model.Class;
import com.stu.administrate.repository.ClassRepository;

@Service
public class StudentService {

	@Autowired
	ClassRepository classRepository;

	public List<Class> getAllClass() {
		return classRepository.selectAllClass();
	}
}
