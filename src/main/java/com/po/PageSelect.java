package com.po;

import java.io.Serializable;
import java.util.List;

public class PageSelect implements Serializable{
	private int page=1;
	private int rows=10;
	private int maxPage;
	private List<?> pageList;
	public PageSelect() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PageSelect(int page, int rows, int maxPage, List<?> pageList) {
		super();
		this.page = page;
		this.rows = rows;
		this.maxPage = maxPage;
		this.pageList = pageList;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public List<?> getPageList() {
		return pageList;
	}
	public void setPageList(List<?> pageList) {
		this.pageList = pageList;
	}
	@Override
	public String toString() {
		return "PageSelect [page=" + page + ", rows=" + rows + ", maxPage=" + maxPage + ", pageList=" + pageList
				+ "]";
	}
	
}
