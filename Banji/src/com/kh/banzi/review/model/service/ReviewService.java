package com.kh.banzi.review.model.service;

import static com.kh.banzi.common.DBCP.getConnection;

import java.io.File;
import java.sql.Connection;
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
	 * @return
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
	 * @return list
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
			
			
			System.out.println("review-s " + review);
			result = dao.insertReview(conn, review);
			
			//---------------------------------------------------글 삽입까지 성공
			
			  if(result>0 && !fList.isEmpty() ) {
		        	result = 0; // result 재활용
		        	
		        	// fList에 저장된 파일정보를 하나씩 DB에 삽입
		        	for(Attachment at : fList) {
		        		// 게시글 번호를 파일 정보에 세팅
		        		at.setParentBoardNo(boardNo);
		        		at.setParentBoardType(boardType);
		        		
		        		result = dao.insertAttachment(conn, at);
		        		
		        		// 반복 삽입 중 한 번 이라도 실패하는 경우 break
		        		if(result == 0) break;
		        	}
		        }
				
			}
			
			// 4. 트랜잭션 처리 및 파일 삭제
			if(result >0) {
				// 모두 삽입 성공 시 게시글 번호를 반환하여 
				// 게시글 상세 조회 화면으로 바로 이동하게함.
				result = boardNo;
				conn.commit();
			}else {
				// 삽입 과정 중 실패한 경우
				// 서버에 미리 저장되어있는 업로드 파일들을 삭제
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







}
