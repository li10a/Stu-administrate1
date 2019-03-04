package com.stu.administrate.exception;

public class LoginException extends Exception {

	private static final long serialVersionUID = 1L;

	private String msgKey;

	public LoginException(String msgKey) {
		this.msgKey = msgKey;
	}

	public String getMsgKey() {
		return msgKey;
	}

	public void setMsgKey(String msgKey) {
		this.msgKey = msgKey;
	}
}
