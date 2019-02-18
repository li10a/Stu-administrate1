package com.stu.administrate.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stu.administrate.dao.SampleDAO;

@Service
public class SampleService {
	@Autowired
	private SampleDAO sampleDAO;

	public int getTotalArticleCount() {
		return sampleDAO.getTotalArticleCount();
	}
}
