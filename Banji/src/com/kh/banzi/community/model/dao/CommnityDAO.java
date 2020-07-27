package com.kh.banzi.community.model.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class CommnityDAO {

    private Properties prop;
    
    public CommnityDAO() throws Exception{
        String fileName 
        = CommnityDAO.class.getResource("/com/kh/wsp/sql/community/community-query.properties").getPath();
        
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
}
