package com.spring.board.vo;

public class PageVo {
	
	private int pageNo = 1;
	private int pageSize = 5;
	private int startIndex; 
    private int endIndex;  

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	 public int getPageSize() {
	        return pageSize;
	    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    
    public int getStartRow() {
        return (pageNo - 1) * pageSize + 1;
    }

    public int getEndRow() {
        return pageNo * pageSize;
    }
    
	
}
