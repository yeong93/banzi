package com.kh.banzi.review.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.banzi.review.model.vo.Attachment;
import com.kh.banzi.review.model.vo.PageInfo;
import com.kh.banzi.review.model.vo.Review;

public class ReviewDAO {
	private Properties prop;
	
	public ReviewDAO() throws FileNotFoundException, IOException {
		String fileName = ReviewDAO.class.getResource("/com/kh/banzi/sql/review/review-query.properties").getPath();
		prop = new Properties();
		prop.load(new FileReader(fileName));
	}

	
	/** 리뷰 게시판 목록 조회 DAO
	 * @param conn
	 * @param pInfo 
	 * @return rList
	 * @throws Exception
	 */
	public List<Review> selectReview(Connection conn) throws Exception {
		Statement stmt = null;
		ResultSet rset = null;
		List<Review> list = null;
		
		String query = prop.getProperty("selectList");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			list = new ArrayList<Review>();
			
			while(rset.next()) {
				Review review = new Review(rset.getInt("REVIEW_BOARD_NO"),
													rset.getInt("REVIEW_WRITER_NO"),
													rset.getString("REVIEW_TITLE"),
													rset.getString("REVIEW_CONTENT"),
													rset.getDate("REVIEW_CREATE_DATE"),
													rset.getDate("REVIEW_MODIFY_DATE"),
													rset.getInt("REVIEW_RATING"),
													rset.getInt("REVIEW_CATEGORY"),
													rset.getString("REVIEW_STATUS"),
													rset.getInt("READ_COUNT"),
													rset.getInt("BOARD_TYPE")
													);
				
				list.add(review);
			}
		}finally {
			rset.close();
			stmt.close();
		}
		return list;
	}


	/** 다음 리뷰글 번호 반환 DAO
	 * @param conn
	 * @return boardNo
	 * @throws Exception
	 */
	public int selectNextNo(Connection conn) throws Exception{
		Statement stmt = null;
		ResultSet rset = null; 
		int boardNo = 0;
		
		String query = prop.getProperty("selectNextNo");
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				boardNo = rset.getInt(1);
			}
			
		}finally {
			rset.close();
			stmt.close();
		}
		
		return boardNo;
	}


	/** 게시'글' 삽입 DAO
	 * @param conn
	 * @param review
	 * @return result
	 */
	public int insertReview(Connection conn, Review review) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertReview");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, review.getReviewBoardNo());
			pstmt.setString(2, review.getUserId());
			pstmt.setString(3, review.getReviewTitle());
			pstmt.setString(4, review.getReviewContent());
			pstmt.setInt(5, review.getReviewCategory());
			pstmt.setInt(6, review.getBoardType());
		
			result = pstmt.executeUpdate();
			
			System.out.println(result);
		}finally {
			pstmt.close();
		}
		
		return result;
	}


	/** 게시글 파일 삽입 DAO
	 * @param conn
	 * @param at
	 * @return result
	 * @throws Exception
	 */
	public int insertAttachment(Connection conn, Attachment at) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertAttachment");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, at.getFileOriginName());
			pstmt.setString(2, at.getFileChangeName());
			pstmt.setString(3, at.getFilePath());
			pstmt.setInt(4, at.getFileLevel());
			pstmt.setInt(5, at.getParentBoardNo());
			pstmt.setInt(6, at.getParentBoardType());
			
			result = pstmt.executeUpdate();
			
			
		}finally {
			pstmt.close();
		}

		return result;
	}
	
	
}
