package com.kh.banzi.community.model.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
                cList.add(new Community(rset.getString("USER_NAME"),
                                        rset.getTimestamp("REG_DATE"),
                                        rset.getString("TITLE"),
                                        rset.getString("CONTENT"),
                                        rset.getInt("VIEWS")));
            }
            
        }finally {
            rset.close();
            pstmt.close();
        }
        
        
        return cList;
    }
}
