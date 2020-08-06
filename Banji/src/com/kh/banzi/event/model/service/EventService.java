package com.kh.banzi.event.model.service;

import static com.kh.banzi.common.DBCP.getConnection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
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

	/** 수정 화면 구성
	 * @param eventNo
	 * @return event
	 * @throws Exception
	 */
	public Event eventUpdateView(int eventNo) throws Exception{
		
		Connection conn = getConnection();
		
		Event event = dao.eventUpdateView(conn, eventNo);
		
		if(event.getEventContent() != null) {
			event.setEventContent(event.getEventContent().replace("<br>", "\r\n"));			
		}
		
		conn.close();
		return event;
	}

	/** 게시글 수정
	 * @param event
	 * @param fList
	 * @return result
	 * @throws Exception
	 */
	public int eventUpdate(Event event, List<Attachment> fList) throws Exception{
		
		Connection conn = getConnection();
		
		int result = 0;
		
		if(event.getEventContent() != null) {
			event.setEventContent(replaceParameter(event.getEventContent())); 
			event.setEventContent(event.getEventContent().replace("\r\n", "<br>")); 
		}
		
		result = dao.eventUpdate(conn, event);
		
		List<Attachment> deleteFiles = new ArrayList<Attachment>();
		
		if(result > 0 && !fList.isEmpty()) {
			
			result = 0; 
			List<Attachment> oldList = dao.selectFiles(conn, event.getEventNo());
			
			boolean flag = false;
			for(Attachment newFile : fList) {
				flag = false; 
				for(Attachment oldFile : oldList) {
					
					if(newFile.getFileLevel() == oldFile.getFileLevel()) {
						flag = true;
						deleteFiles.add(oldFile);
						newFile.setFileNo(oldFile.getFileNo()); 
					}
				}
				newFile.setParentBoardNo(event.getEventNo());
				
				if(flag) { 
					result = dao.updateAttachment(conn, newFile);
				}else { 
					result = dao.insertAttachment(conn, newFile);
				}
				if(result == 0) break;
			}
		}
		
		List<Attachment> tempList = null;
		if(result > 0) {
			result = event.getEventNo();
			conn.commit();
			tempList = deleteFiles;
		}else {
			conn.rollback();
			tempList = fList;
		}
		
		for(Attachment at : tempList) {
			String fileName = at.getFileChangeName();
			String filePath = at.getFilePath();
			
			File deleteFile = new File(filePath+fileName);
			deleteFile.delete();
		}
		
		conn.close();
		return result;
	}

	/** 게시글 삭제
	 * @param eventNo
	 * @return result
	 * @throws Exception
	 */
	public int eventDelete(int eventNo) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.eventDelete(conn, eventNo);
		
		if(result > 0) conn.commit();
		else conn.rollback();
		
		conn.close();
		return result;
	}

	/** 당첨자 발표 글 개수
	 * @param currentPage
	 * @param eventType 
	 * @return pInfo
	 * @throws Exception
	 */
	public static PageInfo winnerPageInfo(String currentPage) throws Exception{
		Connection conn = getConnection();

		int cp = currentPage == null ? 1 : Integer.parseInt(currentPage);

		int listCount = dao.winnerListCount(conn);

		conn.close();
		return new PageInfo(cp, listCount);
	}

	/** 당첨자 글 목록 조회
	 * @param pInfo
	 * @return wList
	 * @throws Exception
	 */
	public List<Event> winnerList(PageInfo pInfo) throws Exception{
		Connection conn = getConnection();
		
		List<Event> wList = dao.winnerList(conn, pInfo);
		
		conn.close();
		return wList;
	}

	/** 당첨자 세부 조회
	 * @param no
	 * @return event
	 * @throws Exception
	 */
	public Event winnerView(int no) throws Exception{
		Connection conn = getConnection();
		
		Event event = dao.winnerView(conn, no);
		
		conn.close();
		return event;
	}

	/** 당첨자 발표 수정
	 * @param no
	 * @param content
	 * @return result
	 * @throws Exception
	 */
	public int changeWinner(int no, String content) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.changeWinner(conn, no, content);
		
		if(result > 0) conn.commit();
		else conn.rollback();
		
		conn.close();
		return result;
	}
	
}
