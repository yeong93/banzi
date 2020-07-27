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
			
			pstmt.executeQuery();
			
		}finally {
			pstmt.close();
		}
		
		return result;
	}
	
	
	
	/** 로그인 DAO
	 * @param conn
	 * @param user
	 * @return loginUser 
	 * @throws Exception
	 */
	public User login(Connection conn, User user) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		User loginUser = null;

		String query = prop.getProperty("loginUser");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPwd());

			rset = pstmt.executeQuery();

			if(rset.next()) {
				loginUser = new User(
						rset.getString("USER_NAME"), 
						rset.getString("USER_EMAIL"), 
						rset.getString("USER_GRADE"), 
						rset.getString("USER_QUESTION"),
						rset.getString("USER_ANSWER"),
						rset.getString("USER_AUTH")+"");
				loginUser.setUserId(user.getUserId());
			}
		} finally {
			rset.close();
			pstmt.close();
		}

		return loginUser;


	}
	
}
