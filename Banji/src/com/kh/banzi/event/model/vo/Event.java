package com.kh.banzi.event.model.vo;

import java.sql.Timestamp;

public class Event {
	
	private int eventNo;
	private String eventWriter;
	private String eventTitle;
	private String eventContent;
	private Timestamp createDay;
	private Timestamp modifyDay;
	private Timestamp startDay;
	private Timestamp endDay;
	private String eventStatus;
	private int boardType = 7;
	private String eventWin;
	private int eventWinNo;
	

	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Event(int eventNo, String eventWriter, String eventTitle, String eventContent, Timestamp createDay,
			Timestamp modifyDay, Timestamp startDay, Timestamp endDay, String eventStatus, int boardType,
			String eventWin, int eventWinNo) {
		super();
		this.eventNo = eventNo;
		this.eventWriter = eventWriter;
		this.eventTitle = eventTitle;
		this.eventContent = eventContent;
		this.createDay = createDay;
		this.modifyDay = modifyDay;
		this.startDay = startDay;
		this.endDay = endDay;
		this.eventStatus = eventStatus;
		this.boardType = boardType;
		this.eventWin = eventWin;
		this.eventWinNo = eventWinNo;
	}





	public Event(int eventNo, String eventWriter, String eventTitle, String eventContent, Timestamp createDay,
			Timestamp startDay, Timestamp endDay) {
		super();
		this.eventNo = eventNo;
		this.eventWriter = eventWriter;
		this.eventTitle = eventTitle;
		this.eventContent = eventContent;
		this.createDay = createDay;
		this.startDay = startDay;
		this.endDay = endDay;
	}


	public Event(String eventWriter, String eventTitle, String eventContent, Timestamp startDay, Timestamp endDay) {
		super();
		this.eventWriter = eventWriter;
		this.eventTitle = eventTitle;
		this.eventContent = eventContent;
		this.startDay = startDay;
		this.endDay = endDay;
	}


	public Event(int eventNo, String eventWriter, String eventTitle, String eventContent, Timestamp createDay,
			Timestamp modifyDay, Timestamp startDay, Timestamp endDay) {
		super();
		this.eventNo = eventNo;
		this.eventWriter = eventWriter;
		this.eventTitle = eventTitle;
		this.eventContent = eventContent;
		this.createDay = createDay;
		this.modifyDay = modifyDay;
		this.startDay = startDay;
		this.endDay = endDay;
	}
	


	public Event(int eventNo, String eventTitle, String eventContent, Timestamp startDay, Timestamp endDay) {
		super();
		this.eventNo = eventNo;
		this.eventTitle = eventTitle;
		this.eventContent = eventContent;
		this.startDay = startDay;
		this.endDay = endDay;
	}
	
	

	public Event(int eventNo, String eventTitle, Timestamp startDay, Timestamp endDay, int eventWinNo) {
		super();
		this.eventNo = eventNo;
		this.eventTitle = eventTitle;
		this.startDay = startDay;
		this.endDay = endDay;
		this.eventWinNo = eventWinNo;
	}


	public Event(int eventNo, String eventTitle, String eventWin) {
		super();
		this.eventNo = eventNo;
		this.eventTitle = eventTitle;
		this.eventWin = eventWin;
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



	public Timestamp getCreateDay() {
		return createDay;
	}



	public void setCreateDay(Timestamp createDay) {
		this.createDay = createDay;
	}



	public Timestamp getModifyDay() {
		return modifyDay;
	}



	public void setModifyDay(Timestamp modifyDay) {
		this.modifyDay = modifyDay;
	}



	public Timestamp getStartDay() {
		return startDay;
	}



	public void setStartDay(Timestamp startDay) {
		this.startDay = startDay;
	}



	public Timestamp getEndDay() {
		return endDay;
	}



	public void setEndDay(Timestamp endDay) {
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



	public String getEventWin() {
		return eventWin;
	}



	public void setEventWin(String eventWin) {
		this.eventWin = eventWin;
	}



	public int getEventWinNo() {
		return eventWinNo;
	}



	public void setEventWinNo(int eventWinNo) {
		this.eventWinNo = eventWinNo;
	}



	@Override
	public String toString() {
		return "Event [eventNo=" + eventNo + ", eventWriter=" + eventWriter + ", eventTitle=" + eventTitle
				+ ", eventContent=" + eventContent + ", createDay=" + createDay + ", modifyDay=" + modifyDay
				+ ", startDay=" + startDay + ", endDay=" + endDay + ", eventStatus=" + eventStatus + ", boardType="
				+ boardType + ", eventWin=" + eventWin + ", eventWinNo=" + eventWinNo + "]";
	}
	

}
