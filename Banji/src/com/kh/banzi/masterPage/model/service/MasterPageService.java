package com.kh.banzi.masterPage.model.service;

import static com.kh.banzi.common.DBCP.getConnection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import com.kh.banzi.event.model.vo.Event;
import com.kh.banzi.masterPage.model.dao.MasterPageDAO;
import com.kh.banzi.user.model.vo.User;

public class MasterPageService {
	
	private MasterPageDAO dao;
	
	public MasterPageService() throws FileNotFoundException, IOException {
		dao = new MasterPageDAO();
	}

	/** 회원 등급이 부여되지 않은 회원 목록 조회
	 * @return aList
	 * @throws Exception
	 */
	public List<User> authList() throws Exception{
		
		Connection conn = getConnection();
		
		List<User> aList = dao.authList(conn);
		
		conn.close();
		return aList;
	}

	/** 회원 등급 부여
	 * @param no
	 * @return result
	 * @throws Exception
	 */
	public int changeAuth(int no) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.changeAuth(conn, no);
		
		if(result > 0) conn.commit();
		else conn.rollback();
		
		conn.close();
		return result;
	}

	/** 당첨자 미발표 이벤트 조회
	 * @return sList
	 * @throws Exception
	 */
	public List<Event> stillList() throws Exception{
		
		Connection conn = getConnection();
		
		List<Event> sList = dao.stillList(conn);
		
		conn.close();
		return sList;
	}

	/** 당첨자 글 삽입
	 * @param no
	 * @param content
	 * @return result 
	 * @throws Exception
	 */
	public int insertWinner(int no, String content) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.insertWinner(conn, no, content);
		
		if(result > 0) conn.commit();
		else conn.rollback();
		
		conn.close();
		return result;
	}

}
