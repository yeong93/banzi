package com.kh.banzi.community.model.vo;

import java.sql.Timestamp;

public class Reply {
    private int replyNo;
    private String regWriter;
    private String content;
    private Timestamp regDate;
    private int boardNo;
    
    public Reply() {
        // TODO Auto-generated constructor stub
    }
    
    

    public Reply(String regWriter, String content, int boardNo) {
        super();
        this.regWriter = regWriter;
        this.content = content;
        this.boardNo = boardNo;
    }



    public Reply(String regWriter, String content, Timestamp regDate) {
        super();
        this.regWriter = regWriter;
        this.content = content;
        this.regDate = regDate;
    }
    public Reply(int replyNo,String regWriter, String content, Timestamp regDate) {
        super();
        this.replyNo = replyNo;
        this.regWriter = regWriter;
        this.content = content;
        this.regDate = regDate;
    }


    public Reply(int replyNo, String regWriter, String content, Timestamp regDate, int boardNo) {
        super();
        this.replyNo = replyNo;
        this.regWriter = regWriter;
        this.content = content;
        this.regDate = regDate;
        this.boardNo = boardNo;
    }

    public int getReplyNo() {
        return replyNo;
    }

    public void setReplyNo(int replyNo) {
        this.replyNo = replyNo;
    }

    public String getRegWriter() {
        return regWriter;
    }

    public void setRegWriter(String regWriter) {
        this.regWriter = regWriter;
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

    public int getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(int boardNo) {
        this.boardNo = boardNo;
    }
    
    

}
