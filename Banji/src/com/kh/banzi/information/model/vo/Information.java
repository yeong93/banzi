package com.kh.banzi.information.model.vo;

import java.sql.Timestamp;

public class Information {
	private int infoBoardNo;
	private String infoBoardTitle;
	private String infoBoardContent;
	private int readCount;
	private Timestamp infoCreateDate;
	private Timestamp infoModifyDate;
	private String infoStatus;
	private int boardType;
	
	// (1) 기본 생성자
	public Information() {
		
	}
	// (2) 매개변수 있는 생성자
	public Information(int infoBoardNo, String infoBoardTitle, String infoBoardContent, int readCount,
			Timestamp infoCreateDate, Timestamp infoModifyDate, String infoStatus, int boardType) {
		super();
		this.infoBoardNo = infoBoardNo;
		this.infoBoardTitle = infoBoardTitle;
		this.infoBoardContent = infoBoardContent;
		this.readCount = readCount;
		this.infoCreateDate = infoCreateDate;
		this.infoModifyDate = infoModifyDate;
		this.infoStatus = infoStatus;
		this.boardType = boardType;
	}
	
	// (3) getter와 setter 작성
	public int getInfoBoardNo() {
		return infoBoardNo;
	}
	public void setInfoBoardNo(int infoBoardNo) {
		this.infoBoardNo = infoBoardNo;
	}
	public String getInfoBoardTitle() {
		return infoBoardTitle;
	}
	public void setInfoBoardTitle(String infoBoardTitle) {
		this.infoBoardTitle = infoBoardTitle;
	}
	public String getInfoBoardContent() {
		return infoBoardContent;
	}
	public void setInfoBoardContent(String infoBoardContent) {
		this.infoBoardContent = infoBoardContent;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public Timestamp getInfoCreateDate() {
		return infoCreateDate;
	}
	public void setInfoCreateDate(Timestamp infoCreateDate) {
		this.infoCreateDate = infoCreateDate;
	}
	public Timestamp getInfoModifyDate() {
		return infoModifyDate;
	}
	public void setInfoModifyDate(Timestamp infoModifyDate) {
		this.infoModifyDate = infoModifyDate;
	}
	public String getInfoStatus() {
		return infoStatus;
	}
	public void setInfoStatus(String infoStatus) {
		this.infoStatus = infoStatus;
	}
	public int getBoardType() {
		return boardType;
	}
	public void setBoardType(int boardType) {
		this.boardType = boardType;
	}
	
	@Override
	public String toString() {
		return "Information [infoBoardNo=" + infoBoardNo + ", infoBoardTitle=" + infoBoardTitle + ", infoBoardContent="
				+ infoBoardContent + ", readCount=" + readCount + ", infoCreateDate=" + infoCreateDate
				+ ", infoModifyDate=" + infoModifyDate + ", infoStatus=" + infoStatus + ", boardType=" + boardType
				+ "]";
	}
	
	
}
