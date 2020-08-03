package com.kh.banzi.event.model.dao;

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

import com.kh.banzi.common.Attachment;
import com.kh.banzi.event.model.vo.Event;
import com.kh.banzi.event.model.vo.PageInfo;

public class EventDAO {
	
	private Properties prop = null;

	public EventDAO() throws FileNotFoundException, IOException{

		String fileName = EventDAO.class
				.getResource("/com/kh/banzi/sql/event/event-query.properties").getPath();

		prop = new Properties();

		prop.load(new FileReader(fileName));
	}

	
	/** 글 개수 조회_게시판 별(evnetType)
	 * @param conn
	 * @param eventType
	 * @return listCount
	 * @throws Exception
	 */
	public int listCount(Connection conn, int eventType) throws Exception{
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int listCount = 0;
		
		String query = prop.getProperty("listCount");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, eventType);
			rset = pstmt.executeQuery();

			if(rset.next()) {
				listCount = rset.getInt(1);
			}

		} finally {
			rset.close();
			pstmt.close();
		}

		return listCount;
	}

	
	public List<Event> eventList(Connection conn, PageInfo pInfo , int eventType) throws Exception{
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Event> eList = null;
		
		String query = prop.getProperty("eventList");
		
		try {
			int startRow = (pInfo.getCurrentPage() - 1) * pInfo.getLimit() + 1;
			int endRow = startRow + pInfo.getLimit() - 1;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, eventType);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rset = pstmt.executeQuery();
			
			eList = new ArrayList<Event>();
			Event e = null;
			while(rset.next()) {
				e = new Event(
						rset.getInt("RNUM"), 
						rset.getString("USER_ID"),
						rset.getString("EVENT_TITLE"),
						rset.getString("EVENT_CONTENT"), 
						rset.getTimestamp("EVENT_START_DT"),
						rset.getTimestamp("EVENT_END_DT")
						);
				eList.add(e);
			}
			
		} finally {
			rset.close();
			pstmt.close();
		}
		
		return eList;
	}

	
	public List<Attachment> fileList(Connection conn, PageInfo pInfo, int eventType) throws Exception{
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Attachment> fList = null;
		
		String query = prop.getProperty("fileList");
		
		try {
			int startRow = (pInfo.getCurrentPage() - 1) * pInfo.getLimit() + 1;
			int endRow = startRow + pInfo.getLimit() - 1;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, eventType);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			fList = new ArrayList<Attachment>();
			Attachment at = null;
			while(rset.next()) {
				at = new Attachment(
						rset.getInt("FILE_NO"), 
						rset.getString("FILE_CHANGE_NAME"), 
						rset.getString("FILE_PATH"), 
						rset.getInt("FILE_LEVEL"));
				fList.add(at);
			}
			
		} finally {
			rset.close();
			pstmt.close();
		}
		return fList;
	}


	/** 글 번호
	 * @param conn
	 * @return eventNo
	 * @throws Exception
	 */
	public int selectNextNo(Connection conn) throws Exception{
	
		Statement stmt = null;
		ResultSet rset = null;
		int eventNo = 0;
		
		String query = prop.getProperty("selectNextNo");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				eventNo = rset.getInt(1);
			}
			
		} finally {
			rset.close();
			stmt.close();
		}
		return eventNo;
	}


	/** 글 삽입
	 * @param conn
	 * @param event
	 * @return result
	 * @throws Exception
	 */
	public int insertEvent(Connection conn, Event event) throws Exception{
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertEvent");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, event.getEventNo());
			pstmt.setString(2, event.getEventWriter());
			pstmt.setString(3, event.getEventTitle());
			pstmt.setString(4, event.getEventContent());
			pstmt.setTimestamp(5, event.getStartDay());
			pstmt.setTimestamp(6, event.getEndDay());
			
			result = pstmt.executeUpdate();
			
		} finally {
			pstmt.close();
		}
		return result;
	}


	/** 파일 삽입
	 * @param conn
	 * @param at
	 * @return result
	 * @throws Exception
	 */
	public int insertAttachment(Connection conn, Attachment at) throws Exception{
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertAttachment");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, at.getFileOriginName());
			pstmt.setString(2, at.getFileChangeName());
			pstmt.setString(3, at.getFilePath());
			pstmt.setInt(4, at.getFileLevel());
			pstmt.setInt(5, at.getParentBoardNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			pstmt.close();
		}
		return result;
	}


	/** 글 상세 조회
	 * @param conn
	 * @param eventNo
	 * @param eventType 
	 * @return event
	 * @throws Exception
	 */
	public Event selectEvent(Connection conn, int eventNo) throws Exception{
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Event event = null;
		
		String query = prop.getProperty("selectEvent");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, eventNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				event = new Event(
						rset.getInt("BOARD_NO"), 
						rset.getString("USER_ID"), 
						rset.getString("EVENT_TITLE"), 
						rset.getString("EVENT_CONTENT"),
						rset.getTimestamp("EVENT_CREATE_DT"),
						rset.getTimestamp("EVENT_MODIFY_DT"),
						rset.getTimestamp("EVENT_START_DT"), 
						rset.getTimestamp("EVENT_END_DT"));
			}
			
		} finally {
			rset.close();
			pstmt.close();
		}
		return event;
	}


	/** 파일 상세 조회
	 * @param conn
	 * @param eventNo
	 * @param eventType 
	 * @return fList
	 * @throws Exception
	 */
	public List<Attachment> selectFiles(Connection conn, int eventNo) throws Exception{
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Attachment> fList = null;
		Attachment at = null;
		
		String query = prop.getProperty("selectFiles");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, eventNo);
			
			fList = new ArrayList<Attachment>();
			rset = pstmt.executeQuery();

				while(rset.next()) {
						at = new Attachment(
								rset.getInt("FILE_NO"),
								rset.getString("FILE_CHANGE_NAME"), 
								rset.getString("FILE_PATH"),
								rset.getInt("FILE_LEVEL"));
						fList.add(at);
						}
			}finally{
				rset.close();
				pstmt.close();
			}
		
		return fList;
	}
	




}
