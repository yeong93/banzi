package com.kh.banzi.qna.model.service;

import static com.kh.banzi.common.DBCP.getConnection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.kh.banzi.common.PageInfo;
import com.kh.banzi.qna.model.dao.QnaDAO;

public class QnaService {
    
    private QnaDAO dao;
    private Connection conn;
    
    public QnaService() throws Exception{
        dao = new QnaDAO();
    }

    public PageInfo getPageInfo(String currentPage) throws Exception{
        conn = getConnection();

        // currentPage가 null인 경우 1, 아닌 경우 정수형으로 파싱
        int cp = (currentPage == null) ? 1 : Integer.parseInt(currentPage);

        // DB에서 전체 게시글 수 조회
        int listCount = dao.getListCount(conn);

        conn.close();

        return new PageInfo(cp, listCount, 3);
    }
    

}
