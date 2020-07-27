package com.kh.banzi.community.model.service;

import static com.kh.banzi.common.DBCP.getConnection;

import java.sql.Connection;

import com.kh.banzi.community.model.dao.CommnityDAO;
import com.kh.banzi.community.model.vo.PageInfo;

public class CommunityService {
    
    private CommnityDAO dao;

    public CommunityService() throws Exception{
       dao = new CommnityDAO();
    }

    /** 페이징 처리 정보 생성 Service
     * @param currentPage
     * @param boardType
     * @return
     * @throws Exception
     */
    public PageInfo getPageInfo(String currentPage) throws Exception{
       Connection conn = getConnection();

       // currentPage가 null인 경우 1, 아닌 경우 정수형으로 파싱
       int cp = (currentPage == null) ? 1 : Integer.parseInt(currentPage);

       // DB에서 전체 게시글 수 조회
       int listCount = dao.getListCount(conn);

       conn.close();

       return new PageInfo(cp, listCount);
    }

}
