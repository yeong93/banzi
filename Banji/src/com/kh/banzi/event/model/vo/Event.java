package com.kh.banzi.event.model.vo;

import java.sql.Date;

public class Event {
	
	private int eventNo;
	private String eventWriter;
	private String eventTitle;
	private String eventContent;
	private String eventFull;
	private Date createDay;
	private Date modifyDay;
	private Date startDay;
	private Date endDay;
	private String eventStatus;
	private int boardType = 7;
	
	
	
	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Event(int eventNo, String eventWriter, String eventTitle, String eventContent, String eventFull,
			Date createDay, Date modifyDay, Date startDay, Date endDay, String eventStatus, int boardType) {
		super();
		this.eventNo = eventNo;
		this.eventWriter = eventWriter;
		this.eventTitle = eventTitle;
		this.eventContent = eventContent;
		this.eventFull = eventFull;
		this.createDay = createDay;
		this.modifyDay = modifyDay;
		this.startDay = startDay;
		this.endDay = endDay;
		this.eventStatus = eventStatus;
		this.boardType = boardType;
	}

	


	public Event(int eventNo, String eventWriter, String eventTitle, String eventContent, Date startDay, Date endDay) {
		super();
		this.eventNo = eventNo;
		this.eventWriter = eventWriter;
		this.eventTitle = eventTitle;
		this.eventContent = eventContent;
		this.startDay = startDay;
		this.endDay = endDay;
	}



	public int getEventNo() {
		return eventNo;
	}



	public void setEventNo(int eventNo) {
		this.eventNo = eventNo;
	}



	public String getEventWriter() {
		return eventWriter;
	}



	public void setEventWriter(String eventWriter) {
		this.eventWriter = eventWriter;
	}



	public String getEventTitle() {
		return eventTitle;
	}



	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}



	public String getEventContent() {
		return eventContent;
	}



	public void setEventContent(String eventContent) {
		this.eventContent = eventContent;
	}



	public String getEventFull() {
		return eventFull;
	}



	public void setEventFull(String eventFull) {
		this.eventFull = eventFull;
	}



	public Date getCreateDay() {
		return createDay;
	}



	public void setCreateDay(Date createDay) {
		this.createDay = createDay;
	}



	public Date getModifyDay() {
		return modifyDay;
	}



	public void setModifyDay(Date modifyDay) {
		this.modifyDay = modifyDay;
	}



	public Date getStartDay() {
		return startDay;
	}



	public void setStartDay(Date startDay) {
		this.startDay = startDay;
	}



	public Date getEndDay() {
		return endDay;
	}



	public void setEndDay(Date endDay) {
		this.endDay = endDay;
	}



	public String getEventStatus() {
		return eventStatus;
	}



	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}



	public int getBoardType() {
		return boardType;
	}



	public void setBoardType(int boardType) {
		this.boardType = boardType;
	}



	@Override
	public String toString() {
		return "Event [eventNo=" + eventNo + ", eventWriter=" + eventWriter + ", eventTitle=" + eventTitle
				+ ", eventContent=" + eventContent + ", eventFull=" + eventFull + ", createDay=" + createDay
				+ ", modifyDay=" + modifyDay + ", startDay=" + startDay + ", endDay=" + endDay + ", eventStatus="
				+ eventStatus + ", boardType=" + boardType + "]";
	}



	

}
