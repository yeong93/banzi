package com.kh.banzi.event.model.vo;

import java.sql.Timestamp;

public class Event {
	
	private int eventNo;
	private String eventTitle;
	private String eventContent;
	private Timestamp createDay;
	private Timestamp modifyDay;
	private Timestamp startDay;
	private Timestamp endDay;
	private int readCount;
	private int eventWriter;
	private int boardType = 4;
	private int eventType;
	
	
	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Event(int eventNo, String eventTitle, String eventContent, Timestamp createDay, Timestamp modifyDay,
			Timestamp startDay, Timestamp endDay, int readCount, int eventWriter, int boardType, int eventType) {
		super();
		this.eventNo = eventNo;
		this.eventTitle = eventTitle;
		this.eventContent = eventContent;
		this.createDay = createDay;
		this.modifyDay = modifyDay;
		this.startDay = startDay;
		this.endDay = endDay;
		this.readCount = readCount;
		this.eventWriter = eventWriter;
		this.boardType = boardType;
		this.eventType = eventType;
	}


	public int getEventNo() {
		return eventNo;
	}


	public void setEventNo(int eventNo) {
		this.eventNo = eventNo;
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


	public int getReadCount() {
		return readCount;
	}


	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}


	public int getEventWriter() {
		return eventWriter;
	}


	public void setEventWriter(int eventWriter) {
		this.eventWriter = eventWriter;
	}


	public int getBoardType() {
		return boardType;
	}


	public void setBoardType(int boardType) {
		this.boardType = boardType;
	}


	public int getEventType() {
		return eventType;
	}


	public void setEventType(int eventType) {
		this.eventType = eventType;
	}


	@Override
	public String toString() {
		return "Event [eventNo=" + eventNo + ", eventTitle=" + eventTitle + ", eventContent=" + eventContent
				+ ", createDay=" + createDay + ", modifyDay=" + modifyDay + ", startDay=" + startDay + ", endDay="
				+ endDay + ", readCount=" + readCount + ", eventWriter=" + eventWriter + ", boardType=" + boardType
				+ ", eventType=" + eventType + "]";
	}
	
	
	

}