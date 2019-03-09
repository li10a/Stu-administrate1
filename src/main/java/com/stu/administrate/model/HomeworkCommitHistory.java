package com.stu.administrate.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("homeworkCommitHistory")
public class HomeworkCommitHistory {

	private int no;

	private int homeworkNo;

	private int studentNo;

	private String homeworkPath;

	private int score;

	private Date registerDate;

	private Date modifyDate;
}
