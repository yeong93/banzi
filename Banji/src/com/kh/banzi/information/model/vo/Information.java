package com.kh.banzi.information.model.vo;

import java.sql.Timestamp;

public class Information {
	private int infoBoardNo;
	private String infoBoardTitle;
	private String infoBoardContent;
	private String userId;
	private int readCount;
	private Timestamp infoBoardCreateDate;
	private Timestamp infoBoardModifyDate;
	private String categoryName;
	private String infoBoardStatus;
	private int boardType;
	
	// (1) 기본 생성자
	public Information() {
		
	}
	// (2) 매개변수 있는 생성자

	public Information(int infoBoardNo, String infoBoardTitle, String infoBoardContent, String userId, int readCount,
			Timestamp infoBoardCreateDate, Timestamp infoBoardModifyDate, String categoryName, String infoBoardStatus,
			int boardType) {
		super();
		this.infoBoardNo = infoBoardNo;
		this.infoBoardTitle = infoBoardTitle;
		this.infoBoardContent = infoBoardContent;
		this.userId = userId;
		this.readCount = readCount;
		this.infoBoardCreateDate = infoBoardCreateDate;
		this.infoBoardModifyDate = infoBoardModifyDate;
		this.categoryName = categoryName;
		this.infoBoardStatus = infoBoardStatus;
		this.boardType = boardType;
	}
	
	// (2)-1 게시글 조회용 매개변수 있는 생성자
	public Information(int infoBoardNo, String infoBoardTitle, String userId, int readCount,
			Timestamp infoBoardModifyDate, String categoryName) {
		super();
		this.infoBoardNo = infoBoardNo;
		this.infoBoardTitle = infoBoardTitle;
		this.userId = userId;
		this.readCount = readCount;
		this.infoBoardModifyDate = infoBoardModifyDate;
		this.categoryName = categoryName;
	}
	
	// (2)-2 게시글 삽입용 매개변수 있는 생성자
	public Information(String infoBoardTitle, String infoBoardContent, String userId, String categoryName,
			int boardType) {
		super();
		this.infoBoardTitle = infoBoardTitle;
		this.infoBoardContent = infoBoardContent;
		this.userId = userId;
		this.categoryName = categoryName;
		this.boardType = boardType;
	}
	

	// (2) - 3 게시글 상세 조회용 매개변수 있는 생성자
	public Information(int infoBoardNo, String infoBoardTitle, String infoBoardContent, String userId, int readCount,
			String categoryName, Timestamp infoBoardCreateDate, Timestamp infoBoardModifyDate) {
		super();
		this.infoBoardNo = infoBoardNo;
		this.infoBoardTitle = infoBoardTitle;
		this.infoBoardContent = infoBoardContent;
		this.userId = userId;
		this.readCount = readCount;
		this.categoryName = categoryName;
		this.infoBoardCreateDate = infoBoardCreateDate;
		this.infoBoardModifyDate = infoBoardModifyDate;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public Timestamp getInfoBoardCreateDate() {
		return infoBoardCreateDate;
	}

	public void setInfoBoardCreateDate(Timestamp infoBoardCreateDate) {
		this.infoBoardCreateDate = infoBoardCreateDate;
	}

	public Timestamp getInfoBoardModifyDate() {
		return infoBoardModifyDate;
	}

	public void setInfoBoardModifyDate(Timestamp infoBoardModifyDate) {
		this.infoBoardModifyDate = infoBoardModifyDate;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getInfoBoardStatus() {
		return infoBoardStatus;
	}

	public void setInfoBoardStatus(String infoBoardStatus) {
		this.infoBoardStatus = infoBoardStatus;
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
				+ infoBoardContent + ", userId=" + userId + ", readCount=" + readCount + ", infoBoardCreateDate="
				+ infoBoardCreateDate + ", infoBoardModifyDate=" + infoBoardModifyDate + ", categoryName="
				+ categoryName + ", infoBoardStatus=" + infoBoardStatus + ", boardType=" + boardType + "]";
	}
	
	
}
