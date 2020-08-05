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
	

	/** info 제목얻어오기- 카테고리1
	 * @return iList
	 * @throws Exception
	 */
	public List<Information> selectInfoList() throws Exception{
		Connection conn = getConnection();
		List<Information> iList = dao.selectInfoList(conn);
		
		conn.close();
		
		return iList;
	}


	// 파일 -카테고리1
	public List<Attachment> selectInfoFileList()throws Exception {
		Connection conn = getConnection();
		List<Attachment> iList = dao.selectInfoFileList(conn);
		conn.close();
		return iList;
	}

	
	/** info 제목얻어오기- 카테고리2
	 * @return iList
	 * @throws Exception
	 */
	public List<Information> selectInfoListTwo() throws Exception{
		Connection conn = getConnection();
		List<Information> iList = dao.selectInfoListTwo(conn);
		
		conn.close();
		
		return iList;
	}


	// 파일 -카테고리2
	public List<Attachment> selectInfoFileListTwo()throws Exception {
		Connection conn = getConnection();
		List<Attachment> iList = dao.selectInfoFileListTwo(conn);
		conn.close();
		return iList;
	}

	
	/** info 제목얻어오기- 카테고리3
	 * @return iList
	 * @throws Exception
	 */
	public List<Information> selectInfoListThree() throws Exception{
		Connection conn = getConnection();
		List<Information> iList = dao.selectInfoListThree(conn);
		
		conn.close();
		
		return iList;
	}


	// 파일 -카테고리3
	public List<Attachment> selectInfoFileListThree()throws Exception {
		Connection conn = getConnection();
		List<Attachment> iList = dao.selectInfoFileListThree(conn);
		conn.close();
		return iList;
	}

	/** info 제목얻어오기- 카테고리4
	 * @return iList
	 * @throws Exception
	 */
	public List<Information> selectInfoListFour() throws Exception{
		Connection conn = getConnection();
		List<Information> iList = dao.selectInfoListFour(conn);
		
		conn.close();
		
		return iList;
	}


	// 파일 -카테고리4
	public List<Attachment> selectInfoFileListFour()throws Exception {
		Connection conn = getConnection();
		List<Attachment> iList = dao.selectInfoFileListFour(conn);
		conn.close();
		return iList;
	}


}
