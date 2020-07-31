package com.kh.banzi.event.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
						rset.getDate("EVENT_START_DT"),
						rset.getDate("EVENT_END_DT")
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

}
