package com.kh.banzi.community.model.vo;

import java.sql.Timestamp;

public class Community {
    private int boardNo;
    private String regName;
    private Timestamp regDate;
    private String title;
    private String content;
    private int views;
    private int boardType;
    
    public Community() {}
    
    
    public Community(String title, String content) {
        super();
        this.title = title;
        this.content = content;
    }
    
    


    public Community(int boardNo, String title, String content) {
        super();
        this.boardNo = boardNo;
        this.title = title;
        this.content = content;
    }


    public Community(String regName, String title, String content, int boardType) {
        super();
        this.regName = regName;
        this.title = title;
        this.content = content;
        this.boardType = boardType;
    }

    public Community(String regName, Timestamp regDate, String title, String content, int views) {
        super();
        this.regName = regName;
        this.regDate = regDate;
        this.title = title;
        this.content = content;
        this.views = views;
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
    

    public Community(int boardNo, String regName, Timestamp regDate, String title, String content, int views,
            int boardType) {
        super();
        this.boardNo = boardNo;
        this.regName = regName;
        this.regDate = regDate;
        this.title = title;
        this.content = content;
        this.views = views;
        this.boardType = boardType;
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
