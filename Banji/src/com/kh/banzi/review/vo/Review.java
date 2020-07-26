package com.kh.banzi.review.vo;

import java.sql.Timestamp;

public class Review {
	private String reviewTitle;
	private String reviewWriter;
	private Timestamp reviewCreateDate;
	private String categoryName;
	private int reviewRating;
	private String reviewContent;
	private String reviewStatus;
	
	public Review() {
		// TODO Auto-generated constructor stub
	}

	public Review(String reviewTitle, String reviewWriter, Timestamp reviewCreateDate, String categoryName,
			int reviewRating, String reviewContent, String reviewStatus) {
		super();
		this.reviewTitle = reviewTitle;
		this.reviewWriter = reviewWriter;
		this.reviewCreateDate = reviewCreateDate;
		this.categoryName = categoryName;
		this.reviewRating = reviewRating;
		this.reviewContent = reviewContent;
		this.reviewStatus = reviewStatus;
	}

	public String getReviewTitle() {
		return reviewTitle;
	}

	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}

	public String getReviewWriter() {
		return reviewWriter;
	}

	public void setReviewWriter(String reviewWriter) {
		this.reviewWriter = reviewWriter;
	}

	public Timestamp getReviewCreateDate() {
		return reviewCreateDate;
	}

	public void setReviewCreateDate(Timestamp reviewCreateDate) {
		this.reviewCreateDate = reviewCreateDate;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getReviewRating() {
		return reviewRating;
	}

	public void setReviewRating(int reviewRating) {
		this.reviewRating = reviewRating;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public String getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	@Override
	public String toString() {
		return "Review [reviewTitle=" + reviewTitle + ", reviewWriter=" + reviewWriter + ", reviewCreateDate="
				+ reviewCreateDate + ", categoryName=" + categoryName + ", reviewRating=" + reviewRating
				+ ", reviewContent=" + reviewContent + ", reviewStatus=" + reviewStatus + "]";
	}
	
	
}
