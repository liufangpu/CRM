package com.atguigu.crm.orm;

import java.util.List;

public class Page<T> {
	private int pageNo;
	
	private int pageSize=3;
	
	private List<T>content;
	
	private int totalElements;
	
	public void setPageNo(int pageNo) {
		if (pageNo<1) {
			pageNo=1;
		}
		this.pageNo = pageNo;
	}
	
	public int getPageNo() {
		return pageNo;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageSize() {
		return pageSize;
	}
	
	public void setContent(List<T> content) {
		this.content = content;
	}
	
	public List<T> getContent() {
		return content;
	}
	public void setTotalElements(int totalElements) {
		this.totalElements = totalElements;
		if (this.pageNo>getTotalPage()) {
			this.pageNo=getTotalPage();
		}
	}
	public int getTotalPage() {
		int totalPage=this.totalElements/this.pageSize;
		if (this.totalElements%this.pageSize!=0) {
			totalPage++;
		}
		return totalPage;
	}

	public int getTotalElements() {
		return totalElements;
	}
	
	public boolean isHasNext() {
	return this.pageNo<getTotalPage();
	}
	
	public int getNext() {
		if (isHasNext()) {
			return this.pageNo+1;
		}
		return this.pageNo;
	}
	
	public boolean isHasPrev() {
		return this.pageNo>1;
	}
	
	public int getPrev() {
		
		if (isHasPrev()) {
			return this.pageNo-1;
		}
		return this.pageNo;
	}

	@Override
	public String toString() {
		return "Page [pageNo=" + pageNo + ", pageSize=" + pageSize + ", content=" + content + ", totalElements=" + totalElements + "]";
	}
	
}
