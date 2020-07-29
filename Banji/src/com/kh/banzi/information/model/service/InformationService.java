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

}
