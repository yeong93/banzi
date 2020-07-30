package com.kh.banzi.event.model.service;

import static com.kh.banzi.common.DBCP.getConnection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import com.kh.banzi.common.Attachment;
import com.kh.banzi.event.model.dao.EventDAO;
import com.kh.banzi.event.model.vo.Event;
import com.kh.banzi.event.model.vo.PageInfo;

public class EventService {
	
	private static EventDAO dao;
	
	public EventService() throws FileNotFoundException, IOException {
		dao = new EventDAO();
	}

	/** 페이징 처리
	 * @param boardType
	 * @param currentPage
	 * @return pInfo
	 * @throws Exception
	 */
	public static PageInfo getPageInfo(String currentPage) throws Exception{
		
		Connection conn = getConnection();
		
		int cp = currentPage == null ? 1 : Integer.parseInt(currentPage);
		
		int listCount = dao.listCount(conn);
		
		conn.close();
		return new PageInfo(cp, listCount);
	}

	/** 진행중인 이벤트 목록
	 * @param pInfo
	 * @return eList
	 * @throws Exception
	 */
	public List<Event> eventList(PageInfo pInfo) throws Exception{
		
		Connection conn = getConnection();

		List<Event> bList = dao.eventList(conn,pInfo);

		conn.close();
		return null;
	}

	/** 파일 목록 조회
	 * @param pInfo
	 * @return fList
	 * @throws Exception
	 */
	public List<Attachment> fileList(PageInfo pInfo) throws Exception{
		Connection conn = getConnection();
		
		List<Attachment> fList = dao.fileList(conn, pInfo);
		
		conn.close();
		return null;
	}

}
