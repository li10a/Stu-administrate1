package com.stu.administrate.util;

import java.util.ArrayList;
import java.util.List;

public class PageInfo {

	private int currentPage = 1;

	private int totalRecord;
	
	private int pageSize = 7;
	
	public PageInfo(int currentPage, int totalRecord) {
		this.currentPage = currentPage;
		this.totalRecord = totalRecord;
	}

	public int getCurrentPage() {
		return this.currentPage;
	}

	public int getTotalPage() {
		return this.totalRecord % this.pageSize > 0 ? this.totalRecord / this.pageSize + 1 : this.totalRecord / this.pageSize;
	}

	public int getStartRow() {
		return this.currentPage * 7 - 6;
	}

	public int getEndRow() {
		int endRow = this.currentPage * 7;
		if (endRow > this.totalRecord) {
			endRow = this.totalRecord;
		}
		return endRow;
	}

	public List<Integer> getDisplayPages() {
		List<Integer> displayPages = new ArrayList<Integer>();
		if (this.currentPage > this.getTotalPage()) {
			this.currentPage = this.getTotalPage();
		}
//		int startPage = this.currentPage - this.currentPage % 5 + 1;
		int startPage = 1;
		if (this.currentPage % 5 == 0) {
			startPage = this.currentPage - 4;
		} else {
			startPage = this.currentPage - this.currentPage % 5 + 1;
		}
		for (int i = startPage; i < startPage + 5; i++) {
			displayPages.add(i);
			if (i == this.getTotalPage()) {
				break;
			}
			
		}
		return displayPages;
	}
}
