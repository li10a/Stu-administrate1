package com.stu.administrate.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("user")
public class User {

	private String id;

	private String name;

	private int age;

	private String password;

	private String sex;

	private int idcardNo;

	private int membershipNo;

	private int phoneNo;

	private int telephoneNo;

	private int qqNo;

	private String email;

	private String address;

	private String type;

	private int classNo;

	private Date registerDate;

	private Date modifyDate;
}
