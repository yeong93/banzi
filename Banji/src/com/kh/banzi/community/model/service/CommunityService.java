package com.kh.banzi.community.model.service;

import static com.kh.banzi.common.DBCP.getConnection;

import java.io.File;
import java.sql.Connection;
import java.util.List;

import com.kh.banzi.common.Attachment;
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

        // DB에서 전체 게시글 수 조회
        int listCount = dao.getListCount(conn);

        conn.close();

        return new PageInfo(cp, listCount, 3);
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

    /** 게시글 등록
     * @param community
     * @param fList
     * @return result
     */
    public int insertBoard(Community community, List<Attachment> fList) throws Exception{
        conn = getConnection();

        int result = 0;

        int boardNo = dao.selectNexNo(conn);

        if (boardNo > 0) {
            community.setBoardNo(boardNo);
            System.out.println(boardNo);

            community.setContent(replaceParameter(community.getContent()));
            community.setContent(community.getContent().replaceAll("\r\n", "<br>"));


            result = dao.insertCommunity(conn, community);
            System.out.println(result);

            if(result > 0 && !fList.isEmpty()) {
                result = 0;

                for(Attachment at : fList) {

                    at.setParentBoardNo(boardNo);
                    at.setParentBoardType(community.getBoardType());
                    
                    result = dao.insertAttachment(conn, at);
                    
                    if(result == 0) break;
                }   
            }
        }

        // 트랜잭션 처리 및 파일 삭제
        if (result > 0) {
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

    /** 썸네일
     * @param pInfo
     * @return fList
     * @throws Exception
     */
    public List<Attachment> selectFileList(PageInfo pInfo) throws Exception{
        conn = getConnection();
        List<Attachment> fList =dao.selectFileList(conn,pInfo);

        conn.close();
        
        return fList;
    }

    /** 게시글 목록 조회
     * @param boardNo
     * @return
     * @throws Exception
     */
    public List<Attachment> selectFiles(int boardNo) throws Exception{
        conn = getConnection();
        List<Attachment> fList = dao.selectFiles(conn, boardNo);
        conn.close();
        return fList;
    }

}
