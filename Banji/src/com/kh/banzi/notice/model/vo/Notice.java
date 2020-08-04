package com.kh.banzi.notice.model.vo;

import java.sql.Timestamp;

public class Notice {
    private int boardNo;
    private String regWriter;
    private String title;
    private String content;
    private Timestamp regDate;
    private int boardType;
    private int views;

    public Notice() {
        // TODO Auto-generated constructor stub
    }
    
    
    
    
    public Notice(String regWriter, String title, String content, int boardType) {
        super();
        this.regWriter = regWriter;
        this.title = title;
        this.content = content;
        this.boardType = boardType;
    }




    public Notice(int boardNo, String regWriter, String title, String content, Timestamp regDate, int views) {
        super();
        this.boardNo = boardNo;
        this.regWriter = regWriter;
        this.title = title;
        this.content = content;
        this.regDate = regDate;
        this.views = views;
    }


    public Notice(int boardNo, String regWriter, String title, String content, Timestamp regDate, int boardType,
            int views) {
        super();
        this.boardNo = boardNo;
        this.regWriter = regWriter;
        this.title = title;
        this.content = content;
        this.regDate = regDate;
        this.boardType = boardType;
        this.views = views;
    }


    public int getBoardType() {
        return boardType;
    }


    public void setBoardType(int boardType) {
        this.boardType = boardType;
    }


    public int getBoardNo() {
        return boardNo;
    }
    
    public void setBoardNo(int boardNo) {
        this.boardNo = boardNo;
    }
    
    public String getRegWriter() {
        return regWriter;
    }
    
    public void setRegWriter(String regWriter) {
        this.regWriter = regWriter;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public Timestamp getRegDate() {
        return regDate;
    }
    
    public void setRegDate(Timestamp regDate) {
        this.regDate = regDate;
    }
    
    public int getViews() {
        return views;
    }
    
    public void setViews(int views) {
        this.views = views;
    }

}
