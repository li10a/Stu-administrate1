package com.stu.administrate.type;

import lombok.Getter;

public enum ForwardPageType {
	FORWARD_ALERT_MSG("/common/forward/alertmsg"),
	FORWARD_GOPAGE("/common/forward/gopage")
	;

	@Getter
	private String forwardPage;

	private ForwardPageType(String forwardPage) {
		this.forwardPage = forwardPage;
	}

}
