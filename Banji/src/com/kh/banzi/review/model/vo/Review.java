package com.kh.banzi.review.model.vo;

import java.sql.Date;

public class Review {
	private int reviewBoardNo;
	private int reviewWriterNo; 
	//-> private String userId; 로변경? 일단 둘다 살리는걸로!
	private String userId;
	private String reviewTitle;
	private String reviewContent;
	private Date reviewCreateDate;
	private Date reviewModifyDate;
	private int reviewRating;
	private int reviewCategory;
	private String reviewStatus;
	private int readCount;
	private int boardType;

	public Review() {	}

	public Review(int reviewBoardNo, int reviewWriterNo, String reviewTitle, String reviewContent,
			Date reviewCreateDate, Date reviewModifyDate, int reviewRating, int reviewCategory, String reviewStatus,
			int readCount, int boardType) {
		super();
		this.reviewBoardNo = reviewBoardNo;
		this.reviewWriterNo = reviewWriterNo;
		this.reviewTitle = reviewTitle;
		this.reviewContent = reviewContent;
		this.reviewCreateDate = reviewCreateDate;
		this.reviewModifyDate = reviewModifyDate;
		this.reviewRating = reviewRating;
		this.reviewCategory = reviewCategory;
		this.reviewStatus = reviewStatus;
		this.readCount = readCount;
		this.boardType = boardType;
	}


	public Review(String userId, String reviewTitle, String reviewContent, int reviewRating, int reviewCategory,
			int boardType) {
		super();
		this.userId = userId;
		this.reviewTitle = reviewTitle;
		this.reviewContent = reviewContent;
		this.reviewRating = reviewRating;
		this.reviewCategory = reviewCategory;
		this.boardType = boardType;
	}

	
	public Review(String userId, String reviewTitle, String reviewContent, int reviewCategory, int boardType) {
		super();
		this.userId = userId;
		this.reviewTitle = reviewTitle;
		this.reviewContent = reviewContent;
		this.reviewCategory = reviewCategory;
		this.boardType = boardType;
	}

	public int getReviewBoardNo() {
		return reviewBoardNo;
	}

	public void setReviewBoardNo(int reviewBoardNo) {
		this.reviewBoardNo = reviewBoardNo;
	}

	public int getReviewWriterNo() {
		return reviewWriterNo;
	}

	public void setReviewWriterNo(int reviewWriterNo) {
		this.reviewWriterNo = reviewWriterNo;
	}

	public String getReviewTitle() {
		return reviewTitle;
	}

	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public Date getReviewCreateDate() {
		return reviewCreateDate;
	}

	public void setReviewCreateDate(Date reviewCreateDate) {
		this.reviewCreateDate = reviewCreateDate;
	}

	public Date getReviewModifyDate() {
		return reviewModifyDate;
	}

	public void setReviewModifyDate(Date reviewModifyDate) {
		this.reviewModifyDate = reviewModifyDate;
	}

	public int getReviewRating() {
		return reviewRating;
	}

	public void setReviewRating(int reviewRating) {
		this.reviewRating = reviewRating;
	}

	public int getReviewCategory() {
		return reviewCategory;
	}

	public void setReviewCategory(int reviewCategory) {
		this.reviewCategory = reviewCategory;
	}

	public String getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public int getBoardType() {
		return boardType;
	}

	public void setBoardType(int boardType) {
		this.boardType = boardType;
	}
	
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Review [reviewBoardNo=" + reviewBoardNo + ", reviewWriterNo=" + reviewWriterNo + ", userId=" + userId
				+ ", reviewTitle=" + reviewTitle + ", reviewContent=" + reviewContent + ", reviewCreateDate="
				+ reviewCreateDate + ", reviewModifyDate=" + reviewModifyDate + ", reviewRating=" + reviewRating
				+ ", reviewCategory=" + reviewCategory + ", reviewStatus=" + reviewStatus + ", readCount=" + readCount
				+ ", boardType=" + boardType + "]";
	}

	


}