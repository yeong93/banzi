package com.kh.banzi.review.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.banzi.review.model.vo.Attachment;
import com.kh.banzi.review.model.vo.PageInfo;
import com.kh.banzi.review.model.vo.Review;
import com.kh.banzi.user.model.vo.User;

public class ReviewDAO {
	private Properties prop;
	
	public ReviewDAO() throws FileNotFoundException, IOException {
		String fileName = ReviewDAO.class.getResource("/com/kh/banzi/sql/review/review-query.properties").getPath();
		prop = new Properties();
		prop.load(new FileReader(fileName));
	}
	

	/** 전체 게시글 수 조회 DAO
	 * @param conn
	 * @param boardType
	 * @return listCount
	 * @throws Exception
	 */
	public int getListCount(Connection conn, int boardType) throws Exception {
		PreparedStatement pstmt =null;
		ResultSet rset = null;
		int listCount = 0;
		String query = prop.getProperty("getListCount"); 
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, boardType);
			rset = pstmt.executeQuery();
			
			if(rset.next()) listCount = rset.getInt(1);
			
		}finally {
			rset.close();
			pstmt.close();
		}
		
		return listCount;
	}
	
	
	/** 리뷰 게시판 목록 조회 DAO
	 * @param conn
	 * @param pInfo 
	 * @param pInfo 
	 * @return rList
	 * @throws Exception
	 */
	public List<Review> selectReview(Connection conn, PageInfo pInfo) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Review> list = null;
		String query = prop.getProperty("selectList");
		
		try {
			int startRow = (pInfo.getCurrentPage()-1)*pInfo.getLimit()+1;
			int endRow = startRow + pInfo.getLimit()-1;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, pInfo.getBoardType());
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Review>();
			
			while(rset.next()) {
				Review review = new Review(rset.getInt("REVIEW_BOARD_NO"),
													rset.getInt("REVIEW_WRITER_NO"),
													rset.getString("REVIEW_TITLE"),
													rset.getString("REVIEW_CONTENT"),
													rset.getDate("REVIEW_CREATE_DATE"),
													rset.getInt("REVIEW_RATING"),
													rset.getInt("REVIEW_CATEGORY"),
													rset.getString("REVIEW_STATUS"),
													rset.getInt("READ_COUNT"),
													rset.getInt("BOARD_TYPE"),
													rset.getString("USER_NAME")
													);
				
				list.add(review);
			}
		}finally {
			rset.close();
			pstmt.close();
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
			pstmt.setInt(5, review.getReviewRating());
			pstmt.setInt(6, review.getReviewCategory());
			pstmt.setInt(7, review.getBoardType());
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


	/** 썸네일 목록 조회 DAO
	 * @param conn
	 * @param pInfo
	 * @return fList
	 * @throws Exception
	 */
	public List<Attachment> selectFileList(Connection conn, PageInfo pInfo) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Attachment> fList = null;
		String query = prop.getProperty("selectFileList");
		
		try {
			// SQL문 조건절에 사용할 값을 가공
			int startRow = (pInfo.getCurrentPage()-1)*pInfo.getLimit()+1;
			
			int endRow = startRow + pInfo.getLimit()-1;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, pInfo.getBoardType());
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
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


	public Review detailReview(Connection conn, int boardNo) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Review review = null;
		String query = prop.getProperty("detailReview");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				review = new Review(
						rset.getInt("REVIEW_BOARD_NO"),
						rset.getString("USER_NAME"),
	                    rset.getString("REVIEW_TITLE"),
	                    rset.getString("REVIEW_CONTENT"),
	                    rset.getDate("REVIEW_CREATE_DATE"),
	                    rset.getInt("REVIEW_CATEGORY"),
	                    rset.getInt("READ_COUNT"),
	                    rset.getString("USER_ID")
	                    );
				
			}
			
		}finally {
			rset.close();
			pstmt.close();
		} 

		return review;
	}


	/** count용 Service
	 * @param conn
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int increaseView(Connection conn, int boardNo) throws Exception{
		 PreparedStatement pstmt = null;
		 int result = 0;
        
		 String query = prop.getProperty("increaseReadCount");
		 try {
			 pstmt = conn.prepareStatement(query);
			 pstmt.setInt(1, boardNo);
			 result = pstmt.executeUpdate();
		 }finally {
			 pstmt.close();
		 }
        return result;
	}


	public List<Attachment> selectFiles(Connection conn, int reviewNo) throws Exception{
		PreparedStatement pstmt = null;
        ResultSet rset = null;
        List<Attachment> fList = null;
        String query = prop.getProperty("selectFiles");
        
        try {
           pstmt = conn.prepareStatement(query);
           pstmt.setInt(1, reviewNo);
           rset = pstmt.executeQuery();
           
           fList = new ArrayList<Attachment>();
           Attachment file = null;
           
           while(rset.next()) {
                 file = new Attachment();
                 file.setFileNo(rset.getInt("FILE_NO"));
                 file.setFileChangeName(rset.getString("FILE_CHANGE_NAME"));
                 file.setFilePath(rset.getString("FILE_PATH"));
                 file.setFileLevel(rset.getInt("FILE_LEVEL"));
                 
                 fList.add(file);
              }
           // System.out.println("에프리스트"+fList);
           
        }finally {
           rset.close();
           pstmt.close();
        }
        
        return fList;

	}


	public int deleteReview(Connection conn, int reviewNo) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("deleteReview");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, reviewNo);
			result = pstmt.executeUpdate();
			
		}finally {
			pstmt.close();
		}
		
		return result;
	}


	public Review updateReview(Connection conn, int reviewNo) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Review review = null;
		String query = prop.getProperty("updateReview");
		 
		try {
			pstmt =conn.prepareStatement(query);
			pstmt.setInt(1, reviewNo);
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				review = new Review(reviewNo,
						rset.getString("REVIEW_TITLE"), 
						rset.getString("REVIEW_CONTENT"),
						rset.getInt("REVIEW_RATING"),
						rset.getInt("REVIEW_CATEGORY")
						);
			}
			System.out.println("디에이이이오"+review);
		}finally {
			rset.close();
			pstmt.close();
		}
		return review;
	}


	/** 게시'글'만 수정
	 * @param conn
	 * @param review
	 * @return result
	 * @throws Exception
	 */
	public int updateReview(Connection conn, Review review) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateView");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, review.getReviewTitle());
			pstmt.setString(2, review.getReviewContent());
			pstmt.setInt(3, review.getReviewCategory());
			pstmt.setInt(4, review.getReviewBoardNo());
			
			result = pstmt.executeUpdate();
		}finally {
			pstmt.close();
		}
		return result;
	}


	/** 리뷰 파일 수정 DAO
	 * @param conn
	 * @param newFile
	 * @return result
	 * @throws Exception
	 */
	public int updateAttachment(Connection conn, Attachment newFile) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateAttachment");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, newFile.getFileOriginName());
			pstmt.setString(2, newFile.getFileChangeName());
			pstmt.setString(3, newFile.getFilePath());
			pstmt.setInt(4, newFile.getFileNo());
			
			result = pstmt.executeUpdate();
		}finally {
			pstmt.close();
		}
		
		return result;

	}


	public List<Review> selectCategoryReview(Connection conn, PageInfo pInfo, int category) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Review> list = null;
		String query = prop.getProperty("selectCategoryList");
		
		try {
			int startRow = (pInfo.getCurrentPage()-1)*pInfo.getLimit()+1;
			int endRow = startRow + pInfo.getLimit()-1;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, pInfo.getBoardType());
			pstmt.setInt(2, category);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Review>();
			
			while(rset.next()) {
				Review review = new Review(rset.getInt("REVIEW_BOARD_NO"),
													rset.getInt("REVIEW_WRITER_NO"),
													rset.getString("REVIEW_TITLE"),
													rset.getString("REVIEW_CONTENT"),
													rset.getDate("REVIEW_CREATE_DATE"),
													rset.getInt("REVIEW_RATING"),
													rset.getInt("REVIEW_CATEGORY"),
													rset.getString("REVIEW_STATUS"),
													rset.getInt("READ_COUNT"),
													rset.getInt("BOARD_TYPE"),
													rset.getString("USER_NAME")
													);
				
				list.add(review);
				
			}
		}finally {
			rset.close();
			pstmt.close();
		}
		return list;
	}

}
