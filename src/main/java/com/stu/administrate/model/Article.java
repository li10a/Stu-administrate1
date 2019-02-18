package com.stu.administrate.model;

import org.apache.ibatis.type.Alias;

@Alias("article")
public class Article {

	private String title;
	private String userName;
	private String contents;
	private String writeTime;
	private Integer num = 0;
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public String getContents() {
		return contents;
	}

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", userName='" + userName + '\'' +
                ", contents='" + contents + '\'' +
                ", writeTime='" + writeTime + '\'' +
                ", num=" + num +
                '}';
    }

	public void setWriteTime(String writeTime) {
		this.writeTime = writeTime;
	}

	public String getWriteTime() {
		return writeTime;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getNum() {
		return num;
	}
}
