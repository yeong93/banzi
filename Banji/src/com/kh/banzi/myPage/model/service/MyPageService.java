package com.kh.banzi.myPage.model.service;

import static com.kh.banzi.common.DBCP.getConnection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;

import com.kh.banzi.myPage.model.dao.MyPageDAO;
import com.kh.banzi.user.model.vo.User;

public class MyPageService {
	
	private MyPageDAO dao;
	
	public MyPageService() throws FileNotFoundException, IOException {
		dao = new MyPageDAO();
	}

	/** 회원정보 수정
	 * @param user
	 * @return result
	 * @throws Exception
	 */
	public int changeUser(User user) throws Exception{
		
		Connection conn = getConnection();
		int result = dao.changeUser(conn, user);
		
		if(result > 0) {
			conn.commit();
		}else {
			conn.rollback();
		}
		
		conn.close();
		return result;
	}

	/** 비밀번호 수정 
	 * @param user
	 * @param newPwd
	 * @return result
	 * @throws Exception
	 */
	public int changePwd(User user, String newPwd) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.checkPwd(conn, user);
		
		if(result > 0) {
			user.setUserPwd(newPwd);
			
			result = dao.changePwd(conn, user);
			if(result > 0) conn.commit();
			else           conn.rollback();
		}else {
			result = -1;
		}
		
		conn.close();
		return result;
	}

	public int secession(User user) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.checkPwd(conn, user);
		
		if(result > 0) {
			result = dao.secession(conn, user);
			if(result > 0) conn.commit();
			else           conn.rollback();
			
		}else {
			result = -1;
		}
	
		conn.close();
		
		return result;
	}

}
