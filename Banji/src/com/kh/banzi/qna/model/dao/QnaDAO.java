package com.kh.banzi.qna.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

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

}
