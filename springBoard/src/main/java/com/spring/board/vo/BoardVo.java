package com.spring.board.vo;

import java.util.List;

public class BoardVo extends PageVo {
	
	private String 	boardType;
	private int 	boardNum;
	private String 	boardTitle;
	private String 	boardComment;
	private String 	creator;
	private String	modifier;
	private int totalCnt;
	
//	private String boardTypeName;
//	
//	private List<String> boardTypeList;
	
	
	public int getTotalCnt() {
		return totalCnt;
	}
	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}
	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	public String getBoardType() {
		return boardType;
	}
	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardComment() {
		return boardComment;
	}
	public void setBoardComment(String boardComment) {
		this.boardComment = boardComment;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	
	
	
//	public String getboardTypeName() {
//		return boardTypeName;
//	}
//	public void setboardTypeName(String boardTypeName) {
//		this.boardTypeName = boardTypeName;
//	}
//	
//
//	public List<String> getboardTypeList() {
//	    return boardTypeList;
//	}
//
//	public void setboardTypeList(List<String> boardTypeList) {
//	    this.boardTypeList = boardTypeList;
//	}

	
	
}
