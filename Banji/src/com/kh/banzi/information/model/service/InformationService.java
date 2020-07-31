package com.kh.banzi.information.model.service;
import static com.kh.banzi.common.DBCP.getConnection;

import java.io.File;
import java.sql.Connection;
import java.util.List;

import com.kh.banzi.common.Attachment;
import com.kh.banzi.common.PageInfo;
import com.kh.banzi.information.model.dao.InformationDAO;
import com.kh.banzi.information.model.vo.Information;

public class InformationService {
	
	private InformationDAO dao;
	
	public InformationService() throws Exception{
		dao = new InformationDAO();
	}


	/** 페이징 처리 정보 생성 Service
	 * @param currentPage
	 * @param boardType
	 * @return pInfo
	 * @throws Exception
	 */
	public PageInfo getPageInfo(String currentPage, int boardType) throws Exception{
		Connection conn = getConnection();
		
		int cp = currentPage == null ? 1 : Integer.parseInt(currentPage);
		
		// DB에서 전체 게시글 수 조회
		int listCount = dao.getListCount(conn, boardType);
		conn.close();
		
		return new PageInfo(cp, listCount, boardType);
	}


	/** 게시글 목록 조회 Service
	 * @param pInfo
	 * @return bList
	 * @throws Exception
	 */
	public List<Information> selectList(PageInfo pInfo) throws Exception{
		Connection conn = getConnection();
		
		List<Information> bList = dao.selectList(conn, pInfo);
		conn.close();
		
		return bList;
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
	

	/** 게시글(글+파일) 삽입 DAO
	 * @param information
	 * @param fList
	 * @return result
	 * @throws Exception
	 */
	public int insertInformation(Information information, List<Attachment> fList) throws Exception{
		Connection conn = getConnection();
		int result = 0;
		
		int infoBoardNo = dao.selectNextNo(conn);
		
		if(infoBoardNo > 0) {
			information.setInfoBoardNo(infoBoardNo);
			information.setInfoBoardContent(replaceParameter(information.getInfoBoardContent()));
			information.setInfoBoardContent(information.getInfoBoardContent().replace("\r\n", "<br>"));
			result = dao.insertInformation(conn, information);
			
			if(result > 0 && !fList.isEmpty()) {
				result = 0;
				
				for(Attachment at : fList) {
					at.setParentBoardNo(infoBoardNo);
					
					result = dao.insertAttachment(conn, at);
					
					if(result == 0) break;
				}
			}
		}
		if(result >0) {
			result = infoBoardNo;
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


	/** 게시글 상세 조회 Service
	 * @param infoBoardNo
	 * @return information
	 * @throws Exception
	 */
	public Information selectInformation(int infoBoardNo) throws Exception{
		Connection conn = getConnection();
		// 1. 게시글 상세 조회
		Information information = dao.selectInformation(conn, infoBoardNo);
		
		// 2. 상세 조회 성공시 조회수 1 증가
		if(information != null) {
			int result = dao.increaseCount(conn, infoBoardNo);
			
			if(result >0) {
				conn.commit();
				// 반환되는 information 객체에 저장된 조회수(ReadCount)를 DB에 맞게 1 증가
				information.setReadCount(information.getReadCount()+1);
			}
		}
		conn.close();
		return information;
	}


	/** 게시글에 포함된 이미지 조회 Serivce
	 * @param infoBoardNo 
	 * @return fList
	 * @throws Exception
	 */
	
	public List<Attachment> selectFiles(int infoBoardNo) throws Exception{
		Connection conn = getConnection();
		
		List<Attachment> fList = dao.selectFiles(conn, infoBoardNo);
		conn.close();
		
		return fList;
	}


	/** 게시글 삭제용 Service
	 * @param infoBoardNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteInformation(int infoBoardNo) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.deleteInformation(conn, infoBoardNo);
		if(result > 0) conn.commit();
		else conn.rollback();
		
		conn.close();
		
		return result;
	}

}
