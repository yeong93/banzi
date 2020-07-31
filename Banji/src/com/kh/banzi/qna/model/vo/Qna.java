package com.kh.banzi.qna.model.vo;

import java.sql.Date;

import javafx.scene.chart.PieChart.Data;

public class Qna {
    private int boardNo;
    private String regWriter;
    private String title;
    private String content;
    private Date regDate;
    private int boardType;
    private int replyCount;
    
    public Qna() {
        // TODO Auto-generated constructor stub
    }
    
    
    
    public Qna(String regWriter, String title, String content, Date regDate) {
        super();
        this.regWriter = regWriter;
        this.title = title;
        this.content = content;
        this.regDate = regDate;
    }



    public Qna(int boardNo, String regWriter, String title, String content, Date regDate, int boardType, int replyCount) {
        super();
        this.boardNo = boardNo;
        this.regWriter = regWriter;
        this.title = title;
        this.content = content;
        this.regDate = regDate;
        this.boardType = boardType;
        this.replyCount = replyCount;
    }
    

    public int getReplyCount() {
        return replyCount;
    }



    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
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
    
    

    public Date getRegDate() {
        return regDate;
    }



    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }



    public int getBoardType() {
        return boardType;
    }

    public void setBoardType(int boardType) {
        this.boardType = boardType;
    }
    
    

}
