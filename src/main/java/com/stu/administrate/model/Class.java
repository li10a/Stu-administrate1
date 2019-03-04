package com.stu.administrate.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("class1")
public class Class {

	private String no;

	private String name;

	private String teacherId;
	
	private String slogan;

	private int studentCnt;

	private Date registerDate;

	private Date modifyDate;
}
