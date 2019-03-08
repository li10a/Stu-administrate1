package com.stu.administrate.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("user")
public class User {

	private int no;

	private String id;

	private String name;

	private int age;

	private String password;

	private String sex;

	private String idcardNo;

	private String membershipNo;

	private String phoneNo;

	private String telephoneNo;

	private String qqNo;

	private String email;

	private String address;

	private String type;

	private int classNo;

	private String image;

	private Date registerDate;

	private Date modifyDate;
}
