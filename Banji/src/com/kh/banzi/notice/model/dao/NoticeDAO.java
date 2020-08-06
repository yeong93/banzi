package com.kh.banzi.notice.model.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.banzi.common.Attachment;
import com.kh.banzi.common.PageInfo;
import com.kh.banzi.notice.model.vo.Notice;

public class NoticeDAO {
    private Properties prop;
    
    public NoticeDAO() throws Exception{
        String fileName = NoticeDAO.class.getResource("/com/kh/banzi/sql/notice/notice-query.properties").getPath();
        prop = new Properties();
        
        prop.load(new FileReader(fileName));
    }

    public List<Notice> selectList(Connection conn, PageInfo pInfo) throws Exception{
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        List<Notice> nList = new ArrayList<>();
        
        String query = prop.getProperty("selectList"); 
        
        try {
            int startRow = (pInfo.getCurrentPage() - 1) * pInfo.getLimit() + 1;
            
            int endRow = startRow + pInfo.getLimit() - 1;
            
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, startRow);
            pstmt.setInt(2, endRow);
            
            rset = pstmt.executeQuery();
            
            while (rset.next()) {
                nList.add(new Notice(
                        rset.getInt("BOARD_NO"),
                        rset.getString("USER_NAME"),
                        rset.getString("TITLE"),
                        rset.getString("CONTENT"),
                        rset.getTimestamp("REG_DATE"),
                        rset.getInt("BOARD_TYPE"),
                        rset.getInt("VIEWS")
                        ));
            }
        }finally {
            rset.close();
            pstmt.close();
        }
        return nList;
    }

    public Notice selectNotice(Connection conn, int boardNo) throws Exception{
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        Notice notice = null;
        
        String query = prop.getProperty("selectNotice");
        
        try {
          pstmt = conn.prepareStatement(query);
          pstmt.setInt(1, boardNo);
          rset = pstmt.executeQuery();
          if(rset.next()) {
              notice = new Notice(
                      rset.getInt("BOARD_NO"),
                      rset.getString("USER_NAME"),
                      rset.getString("TITLE"),
                      rset.getString("CONTENT"),
                      rset.getTimestamp("REG_DATE"),
                      rset.getInt("VIEWS")
                      );
          }
        }finally {
            rset.close();
            pstmt.close();
        }
        
        return notice;
    }

    public int increaseView(Connection conn, int boardNo) throws Exception{
        PreparedStatement pstmt = null;
        int result = 0;
        
        String query = prop.getProperty("increaseView");
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, boardNo);
            result = pstmt.executeUpdate();
        }finally {
            pstmt.close();
        }
        return result;
    }

    public int inserNotice(Connection conn, Notice notice) throws Exception{
        PreparedStatement pstmt = null;
        int result = 0;
        
        String query = prop.getProperty("insertNotice");
        
        try {
           pstmt = conn.prepareStatement(query);
           
           pstmt.setInt(1, notice.getBoardNo());
           pstmt.setString(2, notice.getRegWriter());
           pstmt.setString(3, notice.getTitle());
           pstmt.setString(4, notice.getContent());
           

           result = pstmt.executeUpdate();
        }finally {
           pstmt.close();
        }
        return result;
    }

    public int deleteNotice(Connection conn,int boardNo) throws Exception{
        PreparedStatement pstmt = null;
        int result = 0;
        
        String query = prop.getProperty("deleteNotice");
        
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, boardNo);
            
            result = pstmt.executeUpdate();
        }finally {
            pstmt.close();
        }
        return result;
    }

    public int updateNotice(Connection conn, Notice notice) throws Exception{
        PreparedStatement pstmt = null;
        int result = 0;
        String query =prop.getProperty("updateNotice");
        
        try {
           pstmt = conn.prepareStatement(query);
           pstmt.setString(1, notice.getTitle());
           pstmt.setString(2, notice.getContent());
           pstmt.setInt(3, notice.getBoardNo());
           
           result = pstmt.executeUpdate();

        }finally {
           pstmt.close();
        }
        
        return result;
    }

    public Notice udpateView(Connection conn, int boardNo) throws Exception{
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        Notice notice = null;
        String query = prop.getProperty("updateView");
        
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, boardNo);
            
            rset = pstmt.executeQuery();
            
            if (rset.next()) 
                notice = new Notice(rset.getString("TITLE"),rset.getString("CONTENT"));
        }finally {
            rset.close();
            pstmt.close();
        }
        return notice;
    }

    public int getListCount(Connection conn) throws Exception{
        Statement stmt = null;
        ResultSet rset = null;
        int listCount = 0;

        String query = prop.getProperty("getListCount");
        
        try {
            stmt = conn.createStatement();
            rset = stmt.executeQuery(query);
            
            if(rset.next())
                listCount = rset.getInt(1);
        }finally {
            rset.close();
            stmt.close();
        }


        return listCount;

    }


}
