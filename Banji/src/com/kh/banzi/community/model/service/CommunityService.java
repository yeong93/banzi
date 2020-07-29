package com.kh.banzi.community.model.service;

import static com.kh.banzi.common.DBCP.getConnection;

import java.sql.Connection;
import java.util.List;

import com.kh.banzi.community.model.dao.CommnityDAO;
import com.kh.banzi.community.model.vo.Community;
import com.kh.banzi.community.model.vo.PageInfo;

public class CommunityService {
    
    private CommnityDAO dao;
    private Connection conn;

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
       conn = getConnection();

       // currentPage가 null인 경우 1, 아닌 경우 정수형으로 파싱
       int cp = (currentPage == null) ? 1 : Integer.parseInt(currentPage);
       System.out.println(cp);

       // DB에서 전체 게시글 수 조회
       int listCount = dao.getListCount(conn);

       conn.close();

       return new PageInfo(cp, listCount);
    }

    public List<Community> selectList(PageInfo pInfo) throws Exception {
        conn = getConnection();
        
        List<Community> cList = dao.selectList(conn, pInfo);

        conn.close();

        return cList;
    }

    public Community selectCommunity(int boardNo) throws Exception{
        conn = getConnection();
        
        Community community = dao.selectCommunity(conn, boardNo);
        
        if(community != null) {
            int result = dao.increaseView(conn, boardNo);
            
            if(result > 0) {
                conn.commit();
                community.setViews(community.getViews() + 1);
            }
        }
        
        conn.close();
        
        return community;
    }

}
