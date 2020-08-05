package com.kh.banzi.index.model.service;

import static com.kh.banzi.common.DBCP.getConnection;

import java.sql.Connection;
import java.util.List;

import com.kh.banzi.common.Attachment;
import com.kh.banzi.community.model.vo.Community;
import com.kh.banzi.index.model.dao.indexDAO;
import com.kh.banzi.information.model.vo.Information;
import com.kh.banzi.qna.model.vo.Qna;
import com.kh.banzi.review.model.vo.Review;

public class indexService {
	private indexDAO dao;
	
	public indexService() throws Exception{
		dao = new indexDAO();
	}
	

	/** info 제목얻어오기
	 * @return iList
	 * @throws Exception
	 */
	public List<Information> selectInfoList() throws Exception{
		Connection conn = getConnection();
		List<Information> iList = dao.selectInfoList(conn);
		
		conn.close();
		
		return iList;
	}


	public List<Community> selectCommunityList() throws Exception{
		Connection conn = getConnection();
		List<Community> iList = dao.selectCommunityList(conn);
		conn.close();
		return iList;
	}


	public List<Qna> selectQNAList()throws Exception {
		Connection conn = getConnection();
		List<Qna> iList = dao.selectQNAList(conn);
		conn.close();
		return iList;
	}


	public List<Review> selectReviewList() throws Exception{
		Connection conn = getConnection();
		List<Review> rList = dao.selectReviewList(conn);
		conn.close();
		return rList;
	}


	public List<Attachment> selectInfoFileList()throws Exception {
		Connection conn = getConnection();
		List<Attachment> iList = dao.selectInfoFileList(conn);
		conn.close();
		return iList;
	}


	public List<Attachment> selectCommunityFileList() throws Exception{
		Connection conn = getConnection();
		List<Attachment> iList = dao.selectCommunityFileList(conn);
		conn.close();
		return iList;
	}


	public List<Attachment> selectQNAFileList() throws Exception{
		Connection conn = getConnection();
		List<Attachment> qList = dao.selectQNAFileList(conn);
		conn.close();
		return qList;
	}


	public List<Attachment> selectReviewFileList() throws Exception{
		Connection conn = getConnection();
		List<Attachment> rList = dao.selectReviewFileList(conn);
		conn.close();
		return rList;
	}

}
