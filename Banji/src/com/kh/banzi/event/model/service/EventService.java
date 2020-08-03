package com.kh.banzi.event.model.service;

import static com.kh.banzi.common.DBCP.getConnection;

import java.io.File;
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
	public static PageInfo getPageInfo(String currentPage, int eventType) throws Exception{
		
		Connection conn = getConnection();
		
		int cp = currentPage == null ? 1 : Integer.parseInt(currentPage);
		
		int listCount = dao.listCount(conn, eventType);
		
		conn.close();
		return new PageInfo(cp, listCount);
	}
	
	/** 크로스 사이트 스크립트 방지
	 * @param param
	 * @return result
	 */
	private String replaceParameter(String param) {
		
	String result = param;
	if(param != null) {
		result = result.replaceAll("&", "&amp;");
		result = result.replaceAll("<", "&lt;");
		result = result.replaceAll(">", "&gt;");
		result = result.replaceAll("\"", "&quot;");
	}
	
	return result;
	}
	
	/** 글 목록 조회
	 * @param pInfo
	 * @param eventType
	 * @return eList
	 * @throws Exception
	 */
	public List<Event> eventList(PageInfo pInfo, int eventType) throws Exception{
		
		Connection conn = getConnection();

		List<Event> eList = dao.eventList(conn, pInfo, eventType);

		conn.close();
		return eList;
	}

	/** 파일 목록 조회
	 * @param pInfo
	 * @return fList
	 * @throws Exception
	 */
	public List<Attachment> fileList(PageInfo pInfo, int eventType) throws Exception{
		Connection conn = getConnection();
		
		List<Attachment> fList = dao.fileList(conn, pInfo, eventType);
		
		conn.close();
		return fList;
	}

	/** 글 등록, 파일 등록
	 * @param event
	 * @param fList
	 * @return result
	 * @throws Exception
	 */
	public int insertEvent(Event event, List<Attachment> fList) throws Exception{
		
		Connection conn = getConnection();
		int result = 0;
		
		int eventNo = dao.selectNextNo(conn);
		
		if(eventNo > 0) {
			event.setEventNo(eventNo);
			event.setEventContent(replaceParameter(event.getEventContent()));
			event.setEventContent(event.getEventContent().replace("\r\n", "<br>"));
			result = dao.insertEvent(conn, event);
			
			if(result > 0 && !fList.isEmpty()) {
				result = 0;
				
				for(Attachment at : fList) {
					at.setParentBoardNo(eventNo);
					result = dao.insertAttachment(conn, at);
					if(result == 0) break;
				}
			}
		}
		if(result > 0) {
			result = eventNo;
			conn.commit();
		}else {
			for(Attachment at : fList) {
				String filePath = at.getFilePath();
				String fileName = at.getFileChangeName();
				
				File deleteFile = new File(filePath + fileName);
				deleteFile.delete();
			}
			conn.rollback();
		}
		
		conn.close();
		return result;
	}

	/** 게시글 상세 조회
	 * @param eventNo
	 * @param eventType 
	 * @return event
	 * @throws Exception
	 */
	public Event selectEvent(int eventNo) throws Exception{

		Connection conn = getConnection();
		
		Event event = dao.selectEvent(conn, eventNo);
		
		if(event != null) {
			conn.commit();
		}
		conn.close();
		return event;
	}

	/** 파일 상세 조회
	 * @param eventNo
	 * @param eventType 
	 * @return fList
	 * @throws Exception
	 */
	public List<Attachment> selectFiles(int eventNo) throws Exception{
		
		Connection conn = getConnection();
		
		List<Attachment> fList = dao.selectFiles(conn, eventNo);
		
		conn.close();
		return fList;
	}

}
