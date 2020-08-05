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
		String query = prop.getProperty("selectInfo"); 
		
		
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

	/** 자유게시판 조회용 dao
	 * @param conn
	 * @return cList
	 * @throws Exception
	 */
	public List<Community> selectCommunityList(Connection conn) throws Exception{
		Statement stmt =null;
		ResultSet rset = null;
		List<Community> cList = null;
		String query = prop.getProperty("selectCommunity"); 
		
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			cList = new ArrayList<Community>();
			
			while(rset.next()) {
				Community community = new Community(rset.getString("TITLE"), 
																	rset.getString("CONTENT"),
																	rset.getInt("BOARD_NO"));
				
				cList.add(community);
			}
				
		}finally {
			rset.close();
			stmt.close();
		}
		
		return cList;
	}

	
	/** QNA용
	 * @param conn
	 * @return qList
	 * @throws Exception
	 */
	public List<Qna> selectQNAList(Connection conn) throws Exception{
		Statement stmt =null;
		ResultSet rset = null;
		List<Qna> qList = null;
		String query = prop.getProperty("selectQNA"); 
		
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			qList = new ArrayList<Qna>();
			
			while(rset.next()) {
				Qna qna = new Qna(rset.getString("TITLE"), 
										rset.getString("CONTENT"),
										rset.getInt("BOARD_NO"));
				
				qList.add(qna);
			}
				
		}finally {
			rset.close();
			stmt.close();
		}
		
		return qList;
	}

	public List<Review> selectReviewList(Connection conn) throws Exception{
		Statement stmt =null;
		ResultSet rset = null;
		List<Review> rList = null;
		String query = prop.getProperty("selectReview"); 
		
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			rList = new ArrayList<Review>();
			
			while(rset.next()) {
				Review review = new Review(rset.getString("REVIEW_TITLE"), 
										rset.getString("REVIEW_CONTENT"),
										rset.getInt("REVIEW_BOARD_NO")
						);
				
				rList.add(review);
			}
				// System.out.println(rList);
		}finally {
			rset.close();
			stmt.close();
		}
		
		return rList;
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
		String query = prop.getProperty("selectInfoFileList");
		
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

	/** 자유게시판 이미지 조회용
	 * @param conn
	 * @return rList
	 * @throws Exception
	 */
	public List<Attachment> selectCommunityFileList(Connection conn) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Attachment> fList = null;
		String query = prop.getProperty("selectCommunityFileList");
		
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

	public List<Attachment> selectQNAFileList(Connection conn) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Attachment> fList = null;
		String query = prop.getProperty("selectQNAFileList");
		
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

	public List<Attachment> selectReviewFileList(Connection conn) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Attachment> fList = null;
		String query = prop.getProperty("selectReviewFileList");
		
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
