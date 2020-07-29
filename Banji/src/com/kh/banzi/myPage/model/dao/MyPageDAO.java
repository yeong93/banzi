package com.kh.banzi.myPage.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.Properties;

import com.kh.banzi.user.model.vo.User;

public class MyPageDAO {
	
	private Properties prop = null;

	public MyPageDAO() throws FileNotFoundException, IOException {

		String fileName = MyPageDAO.class
				.getResource("/com/kh/banzi/sql/myPage/myPage-query.properties").getPath();

		prop = new Properties();

		prop.load(new FileReader(fileName));
	}

	/** 회원정보 수정
	 * @param conn
	 * @param user
	 * @return result
	 * @throws Exception
	 */
	public int changeUser(Connection conn, User user) throws Exception{
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("changeUser");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, user.getUserEmail());
			pstmt.setString(2, user.getUserQuestion());
			pstmt.setString(3, user.getUserAnswer());
			pstmt.setString(4, user.getUserId());
			result = pstmt.executeUpdate();
			
		} finally {
			pstmt.close();
		}
		return result;
	}

	/** 비밀번호 수정_비밀번호 일치 여부
	 * @param conn
	 * @param user
	 * @return result
	 * @throws Exception
	 */
	public int checkPwd(Connection conn, User user) throws Exception{
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String query = prop.getProperty("checkPwd");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPwd());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
			
		} finally {
			rset.close();
			pstmt.close();
		}
		return result;
	}

	/** 비밀번호 수정
	 * @param conn
	 * @param user
	 * @return result
	 * @throws Exception
	 */
	public int changePwd(Connection conn, User user) throws Exception{
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("changePwd");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.getUserPwd());
			pstmt.setString(2, user.getUserId());
			
			result = pstmt.executeUpdate();
			
		} finally {
			pstmt.close();
		}
		return result;
	}

	/** 회원 탈퇴
	 * @param conn
	 * @param user
	 * @return result
	 * @throws Exception
	 */
	public int secession(Connection conn, User user) throws Exception{
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("secession");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.getUserId());
			
			result = pstmt.executeUpdate();
			
			
		} finally {
			pstmt.close();
		}
		return result;
	}

	
}
