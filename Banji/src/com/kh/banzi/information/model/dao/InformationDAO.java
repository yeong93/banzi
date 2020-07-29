package com.kh.banzi.information.model.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.banzi.common.PageInfo;
import com.kh.banzi.information.model.vo.Information;

public class InformationDAO {
	private Properties prop = null;
	
	public InformationDAO() throws Exception{
		String fileName = InformationDAO.class.getResource("/com/kh/banzi/sql/information/information-query.properties").getPath();
		prop = new Properties();
		prop.load(new FileReader(fileName));
	}

	/** 전체 게시글 수 조회 DAO
	 * @param conn
	 * @param boardType
	 * @return listCount
	 * @throws Exception
	 */
	public int getListCount(Connection conn, int boardType) throws Exception{
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int listCount = 0;
		String query = prop.getProperty("getListCount");
		// SELECT COUNT(*) FROM V_INFORMATION_LIST WHERE BOARD_TYPE=? AND INFORMATION_BOARD_STATUS='Y'
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardType);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
			
		}finally {
			rset.close();
			pstmt.close();
		}
		
		
		return listCount;
	}

	/** 게시글 목록 조회 DAO
	 * @param conn
	 * @param pInfo
	 * @return bList
	 * @throws Exception
	 */
	public List<Information> selectList(Connection conn, PageInfo pInfo) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Information> bList = null;
		String query = prop.getProperty("selectList");
		
		try {
			int startRow = (pInfo.getCurrentPage()-1) * pInfo.getLimit() + 1;
			int endRow = startRow + pInfo.getLimit() - 1;
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, pInfo.getBoardType());
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			Information information = null;
			bList = new ArrayList<Information>();
			while(rset.next()) {
				information = new Information
						(rset.getInt("INFORMATION_BOARD_NO"), rset.getString("INFORMATION_BOARD_TITLE"), rset.getString("USER_ID"), 
						 rset.getInt("READ_COUNT"), rset.getTimestamp("INFORMATION_BOARD_MODIFY_DT"), rset.getString("CATEGORY_NAME"));
				
				bList.add(information);
			}
			
		}finally {
			rset.close();
			pstmt.close();
		}
		
		
		return bList;
	}

}
