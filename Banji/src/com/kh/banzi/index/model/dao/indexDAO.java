package com.kh.banzi.index.model.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.banzi.common.Attachment;
import com.kh.banzi.community.model.vo.Community;
import com.kh.banzi.information.model.vo.Information;
import com.kh.banzi.qna.model.vo.Qna;
import com.kh.banzi.review.model.dao.ReviewDAO;
import com.kh.banzi.review.model.vo.Review;

public class indexDAO {
	private Properties prop;
	
	public indexDAO() throws Exception{
		String fileName = ReviewDAO.class.getResource("/com/kh/banzi/sql/index/index-query.properties").getPath();
		prop = new Properties();
		prop.load(new FileReader(fileName));
	}

	/** info 제목 조회 DAO
	 * @param conn 
	 * @return iList
	 * @throws Exception
	 */
	public List<Information> selectInfoList(Connection conn) throws Exception{
		Statement stmt =null;
		ResultSet rset = null;
		List<Information> iList = null;
		String query = prop.getProperty("selectInfoNameOne"); 
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			iList = new ArrayList<Information>();
			
			while(rset.next()) {
				Information information = new Information( rset.getInt("INFORMATION_BOARD_NO"),
																	rset.getString("INFORMATION_BOARD_TITLE"), 
																	rset.getString("INFORMATION_BOARD_CONTENT"));
				
				iList.add(information);
			}
				
		}finally {
			rset.close();
			stmt.close();
		}
		
		return iList;
	}

	/** 정보 이미지 조회용
	 * @param conn
	 * @return fList
	 * @throws Exception
	 */
	public List<Attachment> selectInfoFileList(Connection conn) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Attachment> fList = null;
		String query = prop.getProperty("selectInfoOne");
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			fList = new ArrayList<Attachment>();
			Attachment at = null;
			while(rset.next()) {
				at = new Attachment(rset.getInt("FILE_NO"), 
											rset.getInt("BOARD_NO"),
											rset.getString("FILE_CHANGE_NAME"), 
											rset.getString("FILE_PATH"),
											rset.getInt("FILE_LEVEL")
											);
				fList.add(at);
			}
			
		}finally {
			rset.close();
			pstmt.close();
		}
				
		return fList;
	}

	// ------
	
	/** info 제목 조회 DAO
	 * @param conn 
	 * @return iList
	 * @throws Exception
	 */
	public List<Information> selectInfoListTwo(Connection conn) throws Exception{
		Statement stmt =null;
		ResultSet rset = null;
		List<Information> iList = null;
		String query = prop.getProperty("selectInfoNameTwo"); 
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			iList = new ArrayList<Information>();
			
			while(rset.next()) {
				Information information = new Information( rset.getInt("INFORMATION_BOARD_NO"),
																	rset.getString("INFORMATION_BOARD_TITLE"), 
																	rset.getString("INFORMATION_BOARD_CONTENT"));
				
				iList.add(information);
			}
				
		}finally {
			rset.close();
			stmt.close();
		}
		
		return iList;
	}

	/** 정보 이미지 조회용
	 * @param conn
	 * @return fList
	 * @throws Exception
	 */
	public List<Attachment> selectInfoFileListTwo(Connection conn) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Attachment> fList = null;
		String query = prop.getProperty("selectInfoTwo");
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			fList = new ArrayList<Attachment>();
			Attachment at = null;
			while(rset.next()) {
				at = new Attachment(rset.getInt("FILE_NO"), 
											rset.getInt("BOARD_NO"),
											rset.getString("FILE_CHANGE_NAME"), 
											rset.getString("FILE_PATH"),
											rset.getInt("FILE_LEVEL")
											);
				fList.add(at);
			}
			
		}finally {
			rset.close();
			pstmt.close();
		}
				
		return fList;
	}

	//--
	/** info 제목 조회 DAO
	 * @param conn 
	 * @return iList
	 * @throws Exception
	 */
	public List<Information> selectInfoListThree(Connection conn) throws Exception{
		Statement stmt =null;
		ResultSet rset = null;
		List<Information> iList = null;
		String query = prop.getProperty("selectInfoNameThree"); 
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			iList = new ArrayList<Information>();
			
			while(rset.next()) {
				Information information = new Information( rset.getInt("INFORMATION_BOARD_NO"),
																	rset.getString("INFORMATION_BOARD_TITLE"), 
																	rset.getString("INFORMATION_BOARD_CONTENT"));
				
				iList.add(information);
			}
				
		}finally {
			rset.close();
			stmt.close();
		}
		
		return iList;
	}

	/** 정보 이미지 조회용
	 * @param conn
	 * @return fList
	 * @throws Exception
	 */
	public List<Attachment> selectInfoFileListThree(Connection conn) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Attachment> fList = null;
		String query = prop.getProperty("selectInfoThree");
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			fList = new ArrayList<Attachment>();
			Attachment at = null;
			while(rset.next()) {
				at = new Attachment(rset.getInt("FILE_NO"), 
											rset.getInt("BOARD_NO"),
											rset.getString("FILE_CHANGE_NAME"), 
											rset.getString("FILE_PATH"),
											rset.getInt("FILE_LEVEL")
											);
				fList.add(at);
			}
			
		}finally {
			rset.close();
			pstmt.close();
		}
				
		return fList;
	}
	
	// -------------
	
	/** info 제목 조회 DAO
	 * @param conn 
	 * @return iList
	 * @throws Exception
	 */
	public List<Information> selectInfoListFour(Connection conn) throws Exception{
		Statement stmt =null;
		ResultSet rset = null;
		List<Information> iList = null;
		String query = prop.getProperty("selectInfoNameFour"); 
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			iList = new ArrayList<Information>();
			
			while(rset.next()) {
				Information information = new Information( rset.getInt("INFORMATION_BOARD_NO"),
																	rset.getString("INFORMATION_BOARD_TITLE"), 
																	rset.getString("INFORMATION_BOARD_CONTENT"));
				
				iList.add(information);
			}
				
		}finally {
			rset.close();
			stmt.close();
		}
		
		return iList;
	}

	/** 정보 이미지 조회용
	 * @param conn
	 * @return fList
	 * @throws Exception
	 */
	public List<Attachment> selectInfoFileListFour(Connection conn) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Attachment> fList = null;
		String query = prop.getProperty("selectInfoFour");
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			fList = new ArrayList<Attachment>();
			Attachment at = null;
			while(rset.next()) {
				at = new Attachment(rset.getInt("FILE_NO"), 
											rset.getInt("BOARD_NO"),
											rset.getString("FILE_CHANGE_NAME"), 
											rset.getString("FILE_PATH"),
											rset.getInt("FILE_LEVEL")
											);
				fList.add(at);
			}
			
		}finally {
			rset.close();
			pstmt.close();
		}
				
		return fList;
	}
}
