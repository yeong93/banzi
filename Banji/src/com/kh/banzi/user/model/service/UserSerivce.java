package com.kh.banzi.user.model.service;

import static com.kh.banzi.common.DBCP.getConnection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;

import com.kh.banzi.user.model.dao.UserDAO;
import com.kh.banzi.user.model.vo.User;

public class UserSerivce {
	// DAO 생성
	private UserDAO dao;
	
	public UserSerivce() throws FileNotFoundException, IOException{
		dao = new UserDAO();
	}
	
	
	/** 회원 가입 Service
	 * @param user
	 * @return result
	 * @throws Exception
	 */
	public int signUp(User user) throws Exception{
		Connection conn = getConnection();
		int result = dao.signUp(conn, user);
		
		if(result > 0) conn.commit(); // 결과값이 1 이상일 때(업데이트에 성공할 때) 커밋
		else conn.rollback(); // 업데이트 실패시 롤백
		
		conn.close();
		return result;
	}
	
	
}
