package com.stu.administrate.type;

import lombok.Getter;

public enum UserType {
	ADMIN("A"), TEACHER("T"), STUDENT("S");

	@Getter
	private String type;

	private UserType(String type) {
		this.type = type;
	}

}
