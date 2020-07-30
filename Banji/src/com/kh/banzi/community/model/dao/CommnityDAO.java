package com.kh.banzi.community.model.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.banzi.common.Attachment;
import com.kh.banzi.community.model.vo.Community;
import com.kh.banzi.community.model.vo.PageInfo;

public class CommnityDAO {

    private Properties prop;
    
    public CommnityDAO() throws Exception{
        String fileName 
        = CommnityDAO.class.getResource("/com/kh/banzi/sql/community/community-query.properties").getPath();
        
        prop = new Properties();
        
        prop.load(new FileReader(fileName));
    }




    /** 전체 게시글 수 조회 DAO
     * @param conn
     * @param boardType
     * @return listCount
     * @throws Exception
     */
    public int getListCount(Connection conn) throws Exception {
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




    public List<Community> selectList(Connection conn, PageInfo pInfo) throws Exception{
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        List<Community> cList = null;
        
        String query = prop.getProperty("selectList");
        
        try {
            int startRow = (pInfo.getCurrentPage() - 1) * pInfo.getLimit() + 1;
            
            int endRow = startRow + pInfo.getLimit() - 1;
            
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, startRow);
            pstmt.setInt(2, endRow);
            
            rset = pstmt.executeQuery();
            
            cList = new ArrayList<>();
            
            while(rset.next()) {
                cList.add(new Community(
                                        rset.getInt("BOARD_NO"),
                                        rset.getString("USER_NAME"),
                                        rset.getTimestamp("REG_DATE"),
                                        rset.getString("TITLE"),
                                        rset.getString("CONTENT"),
                                        rset.getInt("VIEWS"),
                                        rset.getInt("BOARD_TYPE")
                                        ));
            }
            
        }finally {
            rset.close();
            pstmt.close();
        }
        
        
        return cList;
    }




    public Community selectCommunity(Connection conn, int boardNo) throws Exception{
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        Community community = null;
        
        String query = prop.getProperty("selectCommunity");
        
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, boardNo);
            
            rset = pstmt.executeQuery();
            if(rset.next()) {
                community = new Community(boardNo, rset.getString("USER_NAME"), rset.getTimestamp("REG_DATE"), rset.getString("TITLE"), rset.getString("CONTENT"), rset.getInt("VIEWS"));
            }
        }finally {
            rset.close();
            pstmt.close();
        }
        
        return community;
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




    /** 다음 게시글 번호 반환DAO
     * @param conn 
     * @return boardNo
     * @throws Exception
     */
    public int selectNexNo(Connection conn) throws Exception{
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


    /** 게시글 삽입 DAO
     * @param conn
     * @param community
     * @return 
     * @throws Exception
     */
    public int insertCommunity(Connection conn, Community community) throws Exception{
        PreparedStatement pstmt = null;
        int result = 0;
        
        String query = prop.getProperty("insertCommunity");
        
        try {
           pstmt = conn.prepareStatement(query);
           
           pstmt.setInt(1, community.getBoardNo());
           pstmt.setString(2, community.getRegName());
           pstmt.setString(3, community.getTitle());
           pstmt.setString(4, community.getContent());
           

           result = pstmt.executeUpdate();
        }finally {
           pstmt.close();
        }
        return result;
    }




    /** 파일 등록 DAO
     * @param conn
     * @param at
     * @return result
     * @throws Exception
     */
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




    public List<Attachment> selectFileList(Connection conn, PageInfo pInfo) throws Exception{
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        Community community = null;
        List<Attachment> fList = null;
        String query = prop.getProperty("selectFileList");
        
        try {
            int startRow = (pInfo.getCurrentPage() - 1) * pInfo.getLimit() + 1;
            
            int endRow = startRow + pInfo.getLimit() - 1;
            
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, pInfo.getBoardType());
            pstmt.setInt(2, startRow);
            pstmt.setInt(3, endRow);
            
            rset = pstmt.executeQuery();
            
            fList = new ArrayList<>();
            
            Attachment at = null;
            while(rset.next()) {
                at = new Attachment(
                        rset.getInt("FILE_NO"),
                        rset.getInt("BOARD_NO"),
                        rset.getString("FILE_CHANGE_NAME"),
                        rset.getString("FILE_PATH")
                        );
                fList.add(at);
            }
        }finally {
            rset.close();
            pstmt.close();
        }
        return fList;
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
    
}
