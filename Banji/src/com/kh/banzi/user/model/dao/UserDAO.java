package com.kh.banzi.user.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import com.kh.banzi.user.model.vo.User;

public class UserDAO {
	// 프로퍼티 생성
	private Properties prop = null;
	
	public UserDAO() throws FileNotFoundException, IOException{
		String fileName =
		UserDAO.class.getResource("/com/kh/banzi/sql/user/user-query.properties").getPath();
		
		prop = new Properties();
		prop.load(new FileReader(fileName));
	}

	/** 회원 가입용 DAO
	 * @param conn
	 * @param user
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Connection conn, User user) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("signUp");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPwd());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserEmail());
			pstmt.setString(5, user.getUserQuestion());
			pstmt.setString(6, user.getUserAnswer());
			pstmt.setString(7, user.getUserGrade());
			pstmt.setString(8, user.getUserGrade());
			
			result = pstmt.executeUpdate();
			
		}finally {
			pstmt.close();
		}
		
		return result;
	}

	/** 아이디 중복 검사 DAO
	 * @param conn
	 * @param id 
	 * @return result
	 * @throws Exception
	 */
	public int idDupCheck(Connection conn, String id) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		ResultSet rset = null;
		
		String query = prop.getProperty("idCheck");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
			
		}finally {
			rset.close();
			pstmt.close();
		}
		
		return result;
	}
	
}
