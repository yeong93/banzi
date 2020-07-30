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

import oracle.net.aso.e;

public class EventDAO {
	
	private Properties prop = null;

	public EventDAO() throws FileNotFoundException, IOException{

		String fileName = EventDAO.class
				.getResource("/com/kh/banzi/sql/event/event-query.properties").getPath();

		prop = new Properties();

		prop.load(new FileReader(fileName));
	}

	/** 진행중인 이벤트 수 조회
	 * @param conn
	 * @param boardType
	 * @return listCount
	 * @throws Exception
	 */
	public int listCount(Connection conn) throws Exception{
		
		Statement stmt = null;
		ResultSet rset = null;
		int listCount = 0;

		String query = prop.getProperty("listCount");

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			if(rset.next()) {
				listCount = rset.getInt(1);
			}

		} finally {
			rset.close();
			stmt.close();
		}

		return listCount;
	}

	/** 진행중인 이벤트 목록 조회
	 * @param conn
	 * @param pInfo
	 * @return
	 * @throws Exception
	 */
	public List<Event> eventList(Connection conn, PageInfo pInfo) throws Exception{
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Event> eList = null;
		
		String query = prop.getProperty("eventList");
		
		try {
			int startRow = (pInfo.getCurrentPage() - 1) * pInfo.getLimit() + 1;
			int endRow = startRow + pInfo.getLimit() - 1;
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
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
		
		return null;
	}

	/** 파일 목록 조회
	 * @param conn
	 * @param pInfo
	 * @return fList
	 * @throws Exception
	 */
	public List<Attachment> fileList(Connection conn, PageInfo pInfo) throws Exception{
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Attachment> fList = null;
		
		String query = prop.getProperty("fileList");
		
		try {
			int startRow = (pInfo.getCurrentPage() - 1) * pInfo.getLimit() + 1;
			int endRow = startRow + pInfo.getLimit() - 1;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
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
