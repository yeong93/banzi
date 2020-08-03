package com.kh.banzi.review.model.service;

import static com.kh.banzi.common.DBCP.getConnection;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.kh.banzi.review.model.dao.ReviewDAO;
import com.kh.banzi.review.model.vo.Attachment;
import com.kh.banzi.review.model.vo.PageInfo;
import com.kh.banzi.review.model.vo.Review;

public class ReviewService {
	private ReviewDAO dao;

	public ReviewService() throws Exception{
		dao = new ReviewDAO();
	}
	
	/** 페이징 처리 정보 생성 Service
	 * @param currentPage
	 * @param boardType
	 * @return PageInfo
	 * @throws Exception
	 */
	public PageInfo getPageInfo(String currentPage, int boardType) throws Exception{
		Connection conn = getConnection();
		
		int cp = (currentPage == null) ? 1 : Integer.parseInt(currentPage);
		
		int listCount = dao.getListCount(conn, boardType);
		
		conn.close();
		
		return new PageInfo(cp, listCount, boardType);
	}
	
	/** 리뷰 목록 조회 Service
	 * @param pInfo 
	 * @param pInfo 
	 * @return rlist
	 * @throws Exception
	 */
	public List<Review> selectList(PageInfo pInfo) throws Exception{
		Connection conn = getConnection();
		List<Review> rList = dao.selectReview(conn, pInfo);
		
		for(Review r : rList) {
			if(r.getReviewContent().length() > 200) {
				r.setReviewContent(r.getReviewContent().substring(0, 196) + "...");
			}
		}
		
		conn.close();
		return rList;
		
	}

	
	/** 리뷰 등록 Service (글+이미지)
	 * @param review
	 * @param rList
	 * @param boardType 
	 * @return result
	 * @throws Exception
	 */
	public int insertReview(Review review, List<Attachment> fList, int boardType) throws Exception{
		Connection conn = getConnection();
		int result = 0;
		
		int boardNo = dao.selectNextNo(conn);
		
		if(boardNo >0) {
			review.setReviewBoardNo(boardNo);
			review.setReviewContent(replaceParameter(review.getReviewContent())); // 크로스 사이트 스크립팅 방지
			review.setReviewContent(review.getReviewContent().replace("\r\n", "<br>")); // 개행문자 처리
			
			result = dao.insertReview(conn, review);
			//---------------------------------------------------글 삽입까지 성공
			
			  if(result>0 && !fList.isEmpty() ) {
		        	result = 0;
		        	
		        	for(Attachment at : fList) {
		        		at.setParentBoardNo(boardNo);
		        		at.setParentBoardType(boardType);
		        		
		        		result = dao.insertAttachment(conn, at);
		        		
		        		// 반복 삽입 중 한 번 이라도 실패하는 경우 break
		        		if(result == 0) break;
		        	}
		        }
				
			}
			
			if(result >0) {
				result = boardNo;
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
	
		// 크로스 사이트 스크립트 방지 메소드
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

		/** 썸네일 목록 조회 Service
		 * @param pInfo
		 * @return fList
		 * @throws Exception
		 */
		public List<Attachment> selectFileList(PageInfo pInfo) throws Exception{
			Connection conn = getConnection();
			List<Attachment> fList = dao.selectFileList(conn,pInfo);
			conn.close();
			return fList;
		}

		/** 후기 상세조회+readCount Service
		 * @param boardNo
		 * @return review
		 * @throws Exception
		 */
		public Review detailReview(int boardNo) throws Exception{
			Connection conn = getConnection();
			Review review = dao.detailReview(conn, boardNo);
			
			 if(review != null) {
	            int result = dao.increaseView(conn, boardNo);

	            if(result > 0) {
	                conn.commit();
	                review.setReadCount(review.getReadCount() + 1);
	            }
	        }
			
			conn.close();
			return review;
		}

		/** 목록조회(파일용)
		 * @param boardNo
		 * @return
		 * @throws Exception
		 */
		public List<Attachment> selectFiles(int boardNo) throws Exception{
			Connection conn = getConnection();
	        List<Attachment> fList = dao.selectFiles(conn, boardNo);
	        conn.close();
	        return fList;
		}

		/** 삭제
		 * @param noticeNo
		 * @return
		 * @throws Exception
		 */
		public int deleteReview(int reviewNo) throws Exception{
			Connection conn = getConnection();
			int result = dao.deleteReview(conn, reviewNo);
			if(result > 0) conn.commit();
			else conn.rollback();
			conn.close();
			
			return result;
		}


		/** 리뷰 수정 화면 위임 Service
		 * @param reviewNo
		 * @return review
		 * @throws Exception
		 */
		public Review updateReview(int reviewNo) throws Exception{
			Connection conn = getConnection();
			Review review =dao.updateReview(conn, reviewNo); 
			// review.setReviewContent(review.getReviewContent().replace("<br>", "\r\n")); 
			conn.close();
			return review; 
		}

		/** 리뷰'글' 수정 
		 * @param review
		 * @param fList
		 * @return result
		 * @throws Exception
		 */
		public int updateReview(Review review, List<Attachment> fList) throws Exception{
			Connection conn = getConnection();
			int result = 0;
			
			review.setReviewContent(replaceParameter(review.getReviewContent()));
			review.setReviewContent(review.getReviewContent().replace("\r\n", "<br>")); 
			
			result = dao.updateReview(conn, review);
			
			List<Attachment> deleteFiles = new ArrayList<Attachment>();
		    // ----------------여기까지함 -------------
			
		    if(result>0 && !fList.isEmpty()) {
		    	result = 0; 
		    	
		    	List<Attachment> oldList = dao.selectFiles(conn, review.getReviewBoardNo());
		    	
		    	boolean flag = false; 
		    	for(Attachment newFile : fList) {
		    		
		    		flag = false; 
		    		
		    		for(Attachment oldFile : oldList) {
		    			
		    			if(newFile.getFileLevel() == oldFile.getFileLevel()) {
		    				
		    				flag = true;
		    				deleteFiles.add(oldFile); 
		    				newFile.setFileNo(oldFile.getFileNo());
		    			}
		    		}
		    		newFile.setParentBoardNo(review.getReviewBoardNo()); 
		    		
		    		if(flag) { // update 상황 (flag:true) == 겹칠때
		    			result = dao.updateAttachment(conn, newFile);
		    			
		    		}else { // insert 상황 (새로운 이미지 추가 상황) == 겹치는게 없을때
		    			result = dao.insertAttachment(conn, newFile);
		    		}
		    		
		    		if(result==0) break;
		    	}
		    }
		    
		    
		    List<Attachment> tempList = null;
		    if(result>0) {
		    	result = review.getReviewBoardNo();
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
		    	deleteFile.delete();
		    }
			
		    conn.close();

		    return result;
		}

		public List<Review> selectCategotyList(PageInfo pInfo, int category) throws Exception{
			Connection conn = getConnection();
			List<Review> rList = dao.selectCategoryReview(conn, pInfo, category);
			for(Review r : rList) {
				if(r.getReviewContent().length() > 200) {
					r.setReviewContent(r.getReviewContent().substring(0, 196) + "...");
				}
			}
			
			conn.close();
			return rList;
		}
}
