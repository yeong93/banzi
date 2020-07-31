package com.kh.banzi.qna.model.service;

import static com.kh.banzi.common.DBCP.getConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.kh.banzi.common.PageInfo;
import com.kh.banzi.community.model.vo.Reply;
import com.kh.banzi.qna.model.dao.QnaDAO;
import com.kh.banzi.qna.model.vo.Qna;

public class QnaService {

    private QnaDAO dao;

    public QnaService() throws Exception{
        dao = new QnaDAO();
    }

    public PageInfo getPageInfo(String currentPage) throws Exception{
        Connection conn = getConnection();

        // currentPage가 null인 경우 1, 아닌 경우 정수형으로 파싱
        int cp = (currentPage == null) ? 1 : Integer.parseInt(currentPage);

        // DB에서 전체 게시글 수 조회
        int listCount = dao.getListCount(conn);

        conn.close();

        return new PageInfo(cp, listCount, 5);
    }

    public List<Qna> selectList(PageInfo pInfo) throws Exception{
        Connection conn = getConnection();
        
        List<Qna> qList = dao.selectList(conn, pInfo);
        conn.close();
        return qList;
    }

    public Qna selectQna(int boardNo) throws Exception{
        Connection conn = getConnection();
        
        Qna qna = dao.selectQna(conn, boardNo);
        
        conn.close();
        return qna;
    }

    public List<Reply> selectReply(int boardNo) throws Exception{
        Connection conn = getConnection();
        
        List<Reply> rList = dao.selectReply(conn, boardNo);
        
        conn.close();
        return rList;
    }



}
