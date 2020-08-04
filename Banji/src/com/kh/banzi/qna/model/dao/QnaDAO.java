package com.kh.banzi.qna.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.banzi.common.Attachment;
import com.kh.banzi.common.PageInfo;
import com.kh.banzi.community.model.vo.Reply;
import com.kh.banzi.qna.model.vo.Qna;

/**
 * @author user1
 *
 */
public class QnaDAO {
    
    private Properties prop;
    
    public QnaDAO() throws Exception{
        String fileName = QnaDAO.class.getResource("/com/kh/banzi/sql/qna/qna-query.properties").getPath();
        prop = new Properties();
        
        prop.load(new FileReader(fileName));
    }

    /** 전체 게시글 수 조회 DAO
     * @param conn
     * @return boardType
     * @throws Exception
     */
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

    public List<Qna> selectList(Connection conn, PageInfo pInfo) throws Exception{
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        List<Qna> qList = new ArrayList<>();
        
        String query = prop.getProperty("selectList");
        
        try {
            int startRow = (pInfo.getCurrentPage() - 1) * pInfo.getLimit() + 1;
            
            int endRow = startRow + pInfo.getLimit() - 1;
            
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, startRow);
            pstmt.setInt(2, endRow);
            
            rset = pstmt.executeQuery();
            
            while(rset.next()) {
                qList.add(
                        new Qna(rset.getInt("BOARD_NO"),
                                rset.getString("USER_NAME"),
                                rset.getString("TITLE"),
                                rset.getString("CONTENT"),
                                rset.getTimestamp("REG_DATE"),
                                rset.getInt("BOARD_TYPE"),
                                rset.getInt("REPLY_COUNT"))
                        );
                
            }
        }finally {
            rset.close();
            pstmt.close();
        }
        return qList;
    }

    public Qna selectQna(Connection conn, int boardNo) throws Exception{
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String query = prop.getProperty("selectQna");
        Qna qna = null;
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, boardNo);
            rset = pstmt.executeQuery();
            if(rset.next()) {
                qna = new Qna(rset.getString("USER_NAME"),
                              rset.getString("TITLE"),
                              rset.getString("CONTENT"),
                              rset.getTimestamp("REG_DATE"));
            }
        }finally {
            rset.close();
        }
        return qna;
    }

    public List<Reply> selectReply(Connection conn, int boardNo) throws Exception{
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String query = prop.getProperty("selectReply");
        Reply reply = null;
        List<Reply> rList = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, boardNo);
            rset = pstmt.executeQuery();
            while(rset.next()) {
                reply = new Reply(
                        rset.getInt("REPLY_NO"),
                        rset.getString("USER_NAME"),
                        rset.getString("CONTENT"),
                        rset.getTimestamp("REG_DATE"));
                rList.add(reply);
            }
        }finally {
            pstmt.close();
        }
        return rList;
    }

    /** 다음 게시글 번호 반환 
     * @param conn
     * @return boardNo
     */
    public int selecNextNo(Connection conn) throws Exception{
        Statement stmt = null;
        ResultSet rset = null;
        int boardNo = 0;
        
        String query = prop.getProperty("selectNextNo");
        try {
            stmt = conn.createStatement();
            rset = stmt.executeQuery(query);
            
            if(rset.next())
                boardNo = rset.getInt(1);
        }finally {
            rset.close();
            stmt.close();
        }
        return boardNo;    
        
    }

    public int insertQna(Connection conn, Qna qna) throws Exception{
        PreparedStatement pstmt = null;
        int result = 0;
        
        String query = prop.getProperty("insertQna");
        
        try {
           pstmt = conn.prepareStatement(query);
           
           pstmt.setInt(1, qna.getBoardNo());
           pstmt.setString(2, qna.getRegWriter());
           pstmt.setString(3, qna.getTitle());
           pstmt.setString(4, qna.getContent());
           

           result = pstmt.executeUpdate();
        }finally {
           pstmt.close();
        }
        return result;
    }

    public int insertAttachment(Connection conn, Attachment at) throws Exception{
        PreparedStatement pstmt = null;
        int result = 0;
        String query = prop.getProperty("insertAttachment");
        
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, at.getFileOriginName());
            pstmt.setString(2, at.getFileChangeName());
            pstmt.setString(3, at.getFilePath());
            pstmt.setInt(4, at.getFileLevel());
            pstmt.setInt(5, at.getParentBoardNo());
            pstmt.setInt(6, at.getParentBoardType());
            
            result = pstmt.executeUpdate();
        }finally {
            pstmt.close();
        }
        return result;
    }

    public List<Attachment> selectFiles(Connection conn, int boardNo) throws Exception{
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        List<Attachment> fList = null;
        String query = prop.getProperty("selectFiles");
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, boardNo);
            
            rset = pstmt.executeQuery();
            
            fList = new ArrayList<Attachment>();
            Attachment file = null;
            while(rset.next()) {
                  file = new Attachment();
                  file.setFileNo(rset.getInt("FILE_NO"));
                  file.setFileChangeName(rset.getString("FILE_CHANGE_NAME"));
                  file.setFilePath(rset.getString("FILE_PATH"));
                  file.setFileLevel(rset.getInt("FILE_LEVEL"));
                  
                  fList.add(file);
               }
            
         }finally {
            rset.close();
            pstmt.close();
         }
        return fList;
    }

    public int insertReply(Connection conn, Reply reply) throws Exception{
        PreparedStatement pstmt = null;
        int result = 0;
        String query = prop.getProperty("insertReply");
        
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, reply.getRegWriter());
            pstmt.setString(2, reply.getContent());
            pstmt.setInt(3, reply.getBoardNo());
            
            result = pstmt.executeUpdate();
        }finally {
            pstmt.close();
        }
        return result;
    }
 
    /** QNA 댓글 개수 증가
     * @param conn
     * @param reply
     * @return result
     * @throws Exception
     */
    public int increaseView(Connection conn, Reply reply) throws Exception{
        PreparedStatement pstmt = null;
        int result = 0;
        String query = prop.getProperty("increaseView");
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, reply.getBoardNo());
            result = pstmt.executeUpdate();
        }finally {
            pstmt.close();
        }
        return result; 
    }

    public Qna updateView(Connection conn, int boardNo) throws Exception{
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        Qna qna = null;
        String query = prop.getProperty("updateView");
        
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, boardNo);
            
            rset = pstmt.executeQuery();
            
            if (rset.next()) {
                qna = new Qna(boardNo, 
                              rset.getString("TITLE"),
                              rset.getString("CONTENT"),
                              rset.getInt("BOARD_TYPE"));
            }
        }finally {
            rset.close();
            pstmt.close();
        }
        return qna;
    }

    /** QNA 수정
     * @param conn 
     * @param qna
     * @return result
     * @throws Exception
     */
    public int updateBoard(Connection conn, Qna qna) throws Exception{
        PreparedStatement pstmt = null;
        int result = 0;
        String query =prop.getProperty("updateQna");
        
        try {
           pstmt = conn.prepareStatement(query);
           pstmt.setString(1, qna.getTitle());
           pstmt.setString(2, qna.getContent());
           pstmt.setInt(3, qna.getBoardNo());
           
           result = pstmt.executeUpdate();

        }finally {
           pstmt.close();
        }
        
        return result;
    }

    public int updateAttachment(Connection conn, Attachment newFile) throws Exception{
        PreparedStatement pstmt = null;
        int result = 0;
        String query = prop.getProperty("updateAttachment");
        
        try {
           pstmt = conn.prepareStatement(query);
           
           pstmt.setString(1, newFile.getFileOriginName());
           pstmt.setString(2, newFile.getFileChangeName());
           pstmt.setString(3, newFile.getFilePath());
           pstmt.setInt(4, newFile.getFileNo());
           
           result = pstmt.executeUpdate();
        }finally {
           pstmt.close();
        }
        
        return result;
    }

    public int deleteReply(Connection conn, int replyNo) throws Exception{
        PreparedStatement pstmt = null;
        int result = 0;
        String query = prop.getProperty("deleteReply");
        
        try {
            pstmt = conn.prepareStatement(query);
            
            pstmt.setInt(1, replyNo);
            result = pstmt.executeUpdate();
        }finally{
            pstmt.close();
        }
        return result;
    }

}
