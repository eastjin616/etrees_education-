package com.spring.board.vo;

import java.util.List;

public class PageVo {
	
	private Integer pageNo = 1;
	private int pageSize = 5;
	private int startIndex; 
    private int endIndex;  
    private int startRow;
    private int endRow;
    
	private String boardTypeName;

	private List<String> boardTypeList;
	
    
//	===========================
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer  pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getEndRow() {
		return endRow;
	}
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	
	public String getboardTypeName() {
		return boardTypeName;
	}
	public void setboardTypeName(String boardTypeName) {
		this.boardTypeName = boardTypeName;
	}
	

	public List<String> getBoardTypeList() {
	    return boardTypeList;
	}

	public void setBoardTypeList(List<String> boardTypeList) {
	    this.boardTypeList = boardTypeList;
	}



//	public int getPageNo() {
//		return pageNo;
//	}
//
//	public void setPageNo(int pageNo) {
//		this.pageNo = pageNo;
//	}
//	
//	 public int getPageSize() {
//	        return pageSize;
//	    }
//    public void setPageSize(int pageSize) {
//        this.pageSize = pageSize;
//    }
    
//    public int getStartRow() {
//        return (pageNo - 1) * pageSize + 1;
//    }
//
//    public int getEndRow() {
//        return pageNo * pageSize;
//    }
 
	
}
