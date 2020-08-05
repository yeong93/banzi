package com.kh.banzi.masterPage.model.dao;

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

import com.kh.banzi.event.model.vo.Event;
import com.kh.banzi.user.model.vo.User;

public class MasterPageDAO {
	
	private Properties prop;

	public MasterPageDAO() throws FileNotFoundException, IOException{

		String fileName = MasterPageDAO.class
				.getResource("/com/kh/banzi/sql/masterPage/masterPage-query.properties").getPath();

		prop = new Properties();

		prop.load(new FileReader(fileName));
	}
	

	/** 회원 등급이 부여되지 않은 회원 목록 조회
	 * @param conn
	 * @return aList
	 * @throws Exception
	 */
	public List<User> authList(Connection conn) throws Exception{
		
		Statement stmt = null;
		ResultSet rset = null;
		
		List<User> aList = null;
		String query = prop.getProperty("authList");
		
		try {
			
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			aList = new ArrayList<User>();
			
			while(rset.next()) {
				User user = new User(
						rset.getInt("USER_NO"),
						rset.getString("USER_ID"), 
						rset.getString("USER_GRADE"));
				aList.add(user);
			}
			
		} finally {
			rset.close();
			stmt.close();
		}
		return aList;
	}


	/** 회원 등급 부여
	 * @param conn
	 * @param no
	 * @return result 
	 * @throws Exception
	 */
	public int changeAuth(Connection conn, int no) throws Exception{
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("changeAuth");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
			
		} finally {
			pstmt.close();
		}
		
		return result;
	}


	public List<Event> stillList(Connection conn) throws Exception{
		Statement stmt = null;
		ResultSet rset = null;
		
		List<Event> sList = null;
		String query = prop.getProperty("stillList");
		
		try {
			
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			sList = new ArrayList<Event>();
			Event e = null;
			
			while(rset.next()) {
				e = new Event(
						rset.getInt("BOARD_NO"), 
						rset.getString("EVENT_TITLE"),
						rset.getTimestamp("EVENT_START_DT"),
						rset.getTimestamp("EVENT_END_DT"),
						rset.getInt("EVENT_WIN_NO") 
						);
				sList.add(e);
			}
			
		} finally {
			rset.close();
			stmt.close();
		}
		return sList;
	}

}
