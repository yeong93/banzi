package com.kh.banzi.information.model.service;
import static com.kh.banzi.common.DBCP.getConnection;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
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
	 * @param category 
	 * @return pInfo
	 * @throws Exception
	 */
	public PageInfo getPageInfo(String currentPage, int boardType, String category) throws Exception{
		Connection conn = getConnection();
		
		int cp = currentPage == null ? 1 : Integer.parseInt(currentPage);
		
		// DB에서 전체 게시글 수 조회
		int listCount = dao.getListCount(conn, boardType,category);
		conn.close();
		
		return new PageInfo(cp, listCount, boardType,category);
	}


	/** 게시글 목록 조회 Service
	 * @param pInfo
	 * @param category 
	 * @return bList
	 * @throws Exception
	 */
	public List<Information> selectList(PageInfo pInfo, String category) throws Exception{
		Connection conn = getConnection();
		
		List<Information> bList = dao.selectList(conn, pInfo, category);
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


	/** 게시글 수정 화면 구성 View
	 * @param infoBoardNo
	 * @return information
	 * @throws Exception
	 */
	public Information updateView(int infoBoardNo) throws Exception{
		
		Connection conn = getConnection();
		Information information = dao.updateView(conn, infoBoardNo);
		// 개행문자 처리
		information.setInfoBoardContent(information.getInfoBoardContent().replace("<br>", "\r\n"));
		conn.close();
		
		return information;
	}


	/** 게시글 수정 Service
	 * @param information
	 * @param fList
	 * @return result
	 * @throws Exception
	 */
	public int updateInformation(Information information, List<Attachment> fList) throws Exception{
		Connection conn = getConnection();
		int result = 0;
		information.setInfoBoardContent(replaceParameter(information.getInfoBoardContent()));
		information.setInfoBoardContent(information.getInfoBoardContent().replace("\r\n", "<br>"));
		
		// 게시글을 수정하는 DAO를 호출
		result = dao.updateInformation(conn, information);
		
		List<Attachment> deleteFiles = new ArrayList<Attachment>();
		
		if(result >0 && !fList.isEmpty()) {
			result = 0; // result 재사용
			
			// 기존 해당 게시글에 포함되었던 파일 정보를 DB로부터 얻어옴.
			List<Attachment> oldList =  dao.selectFiles(conn, information.getInfoBoardNo());
			boolean flag = false;
			
			for(Attachment newFile : fList) {
				// 새로운 파일의 목록의 요소(newFile)에 순차적으로 접근
				flag = false; // flag 초기화
				
				for(Attachment oldFile : oldList) {
					// 기존 파일 목록의 요소(oldFile)에 순차적으로 접근
					if(newFile.getFileLevel() == oldFile.getFileLevel()) {
						flag = true;
						deleteFiles.add(oldFile);
						newFile.setFileNo(oldFile.getFileNo());
					}
				}
				newFile.setParentBoardNo(information.getInfoBoardNo());
				// flag 상태에 따라 알맞은 dao 호출
				if(flag) { // update 상황(파일이 겹침)
					result = dao.updateAttachment(conn, newFile);
				}else { // insert 상황(파일이 겹치지 않음)
					result = dao.insertAttachment(conn, newFile);
				}
				// result == 0이라는 것은, update나 insert가 실패했음을 뜻함
				if(result == 0); break;
			}
		}
		// 트랜잭션 처리 + 삭제 처리
		List<Attachment> tempList = null;
		
		// service의 모든 동작이 성공적으로 진행된 경우 deleteFiles에 담긴 기존 파일을 삭제해야 되고,
		// service 동작 중 오류 또는 실패 발생시 fList에 담긴 새로운 파일을 삭제해야 함.
		if(result > 0) {
			// 수정 완료 후 해당 게시글 상세보기를 위해 result에 글 번호를 저장하여 반환
			result = information.getInfoBoardNo();
			conn.commit();
			tempList = deleteFiles;
		}else {
			conn.rollback();
			tempList = fList;
		}
		// 서버에 저장된 파일 삭제
		for(Attachment at : tempList) {
			String filePath = at.getFilePath();
			String fileName = at.getFileChangeName();
			
			File deleteFile = new File(filePath + fileName);
		}
		conn.close();
		
		return result;
	}

}
