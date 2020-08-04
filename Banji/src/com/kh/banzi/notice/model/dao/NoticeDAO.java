package com.kh.banzi.notice.model.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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


}
