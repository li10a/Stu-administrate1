package com.stu.administrate.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("homework")
public class Homework {

	private int no;

	private String title;

	private String task;

	private int classNo;

	private Date registerDate;

	private Date modifyDate;
}
