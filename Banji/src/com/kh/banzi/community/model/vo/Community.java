package com.kh.banzi.community.model.vo;

import java.sql.Timestamp;

public class Community {
    private int boardNo;
    private String regName;
    private Timestamp regDate;
    private String title;
    private String content;
    private int views;
    
    public Community() {
        // TODO Auto-generated constructor stub
    }

    public Community(int boardNo, String regName, Timestamp regDate, String title, String content, int views) {
        super();
        this.boardNo = boardNo;
        this.regName = regName;
        this.regDate = regDate;
        this.title = title;
        this.content = content;
        this.views = views;
    }

    public int getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(int boardNo) {
        this.boardNo = boardNo;
    }

    public String getRegName() {
        return regName;
    }

    public void setRegName(String regName) {
        this.regName = regName;
    }

    public Timestamp getRegDate() {
        return regDate;
    }

    public void setRegDate(Timestamp regDate) {
        this.regDate = regDate;
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

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
    
    

}
