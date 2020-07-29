package com.kh.banzi.information.model.service;
import static com.kh.banzi.common.DBCP.getConnection;

import java.sql.Connection;
import java.util.List;

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
		
		return null;
	}

}
