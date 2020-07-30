package com.kh.banzi.information.model.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.banzi.common.Attachment;
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

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
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

	/** 다음 게시글 번호 반환 DAO
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int selectNextNo(Connection conn) throws Exception{
		
		Statement stmt = null;
		ResultSet rset = null;
		int infoBoardNo = 0;
		
		String query = prop.getProperty("selectNextNo");
		// SELECT SEQ_INF.NEXTVAL FROM DUAL
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				infoBoardNo = rset.getInt(1);
			}
			
		}finally {
			rset.close();
			stmt.close();
		}
		
		return infoBoardNo;
	}

	/** 게시글 삽입 DAO
	 * @param conn
	 * @param information
	 * @return result
	 * @throws Exception
	 */
	public int insertInformation(Connection conn, Information information) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertInformation");
		try {
			pstmt = conn.prepareStatement(query);
			// INSERT INTO INFORMATION_BOARD(INFORMATION_BOARD_NO, INFORMATION_BOARD_TITLE,
			// INFORMATION_BOARD_CONTENT, INFORMATION_BOARD_WRITER,INFORMATION_CATEGORY, BOARD_TYPE)
			// VALUES (?, ?, (SELECT USER_NO FROM USER_TBL WHERE USER_NO=?), ?, ?)
			pstmt.setInt(1, information.getInfoBoardNo());
			pstmt.setString(2, information.getInfoBoardTitle());
			pstmt.setString(3, information.getInfoBoardContent());
			pstmt.setString(4, information.getUserId());
			pstmt.setString(5, information.getCategoryName());
			pstmt.setInt(6, information.getBoardType());
			
			result = pstmt.executeUpdate();
			
		}finally {
			pstmt.close();
		}
		
		return result;
	}

	/** 파일 등록 DAO
	 * @param conn
	 * @param at
	 * @return result
	 * @throws Exception
	 */
	public int insertAttachment(Connection conn, Attachment at) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertAttachment");
		// insertAttachment = INSERT INTO ATTACHMENT VALUES(SEQ_FNO.NEXTVAL, 2, ?, ?, ?, ?, DEFAULT)
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, at.getParentBoardNo());
			pstmt.setString(2, at.getFileOriginName());
			pstmt.setString(3, at.getFileChangeName());
			pstmt.setString(4, at.getFilePath());
			pstmt.setInt(5, at.getFileLevel());
			
			result = pstmt.executeUpdate();
			
		}finally {
			pstmt.close();
		}
		
		return result;
	}

	/** 게시글 상세 조회용 DAO
	 * @param conn
	 * @param infoBoardNo
	 * @return information
	 * @throws Exception
	 */
	public Information selectInformation(Connection conn, int infoBoardNo) throws Exception{
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Information information = null;
		String query = prop.getProperty("selectInformation");
		
		try {
			pstmt = conn.prepareStatement(query);
			// SELECT * FROM V_INFORMATION_LIST WHERE INFORMATION_BOARD_NO= ? AND INFORMATION_BOARD_STATUS='Y'
			pstmt.setInt(1, infoBoardNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				information = new Information
			(rset.getInt("INFORMATION_BOARD_NO"), rset.getString("INFORMATION_BOARD_TITLE"),
			 rset.getString("INFORMATION_BOARD_CONTENT"), 
			 rset.getString("USER_ID"), rset.getInt("READ_COUNT"), 
			 rset.getString("CATEGORY_NAME"),
			 rset.getTimestamp("INFORMATION_BOARD_CREATE_DT"),
			 rset.getTimestamp("INFORMATION_BOARD_MODIFY_DT"));
			}
			
		}finally {
			rset.close();
			pstmt.close();
		}
		
		return information;
	}

	/** 조회수 증가 DAO
	 * @param conn
	 * @param infoBoardNo
	 * @return result
	 * @throws Exception
	 */
	public int increaseCount(Connection conn, int infoBoardNo) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("increaseCount");
		// UPDATE INFORMATION_BOARD SET READ_COUNT = READ_COUNT+1 WHERE INFORMATION_BOARD_NO = ?
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, infoBoardNo);
			result = pstmt.executeUpdate();
			
		}finally {
			pstmt.close();
		}
		
		return result;
	}

	/** 게시글에 포함된 이미지 조회 DAO
	 * @param conn
	 * @param infoBoardNo
	 * @return fList
	 * @throws Exception
	 */
	public List<Attachment> selectFiles(Connection conn, int infoBoardNo) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Attachment> fList = null;
		String query = prop.getProperty("selectFiles");
		Attachment at = null;
		
		/*
		SELECT FILE_NO, FILE_CHANGE_NAME, FILE_PATH, FILE_LEVEL 
		FROM ATTACHMENT 
		WHERE BOARD_NO=? AND BOARD_TYPE = 2 AND FILE_STATUS = 'Y'
		*/
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, infoBoardNo);
			
			rset = pstmt.executeQuery();
			fList = new ArrayList<Attachment>();

				while(rset.next()) {
						at = new Attachment(rset.getInt("FILE_NO"),
								rset.getString("FILE_CHANGE_NAME"), rset.getString("FILE_PATH"),
								rset.getInt("FILE_LEVEL"));
						fList.add(at);
						}
				

			}finally{
				rset.close();
				pstmt.close();
			}
		
		return fList;
	}

	/** 게시글 삭제용 DAO
	 * @param conn
	 * @param infoBoardNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteInformation(Connection conn, int infoBoardNo) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("deleteInformation");
		// UPDATE INFORMATION_BOARD SET INFORMATION_BOARD_STATUS='N' WHERE INFORMATION_BOARD_NO=?
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, infoBoardNo);
			result = pstmt.executeUpdate();
			
		}finally {
			pstmt.close();
		}
		
		return result;
	}


}
