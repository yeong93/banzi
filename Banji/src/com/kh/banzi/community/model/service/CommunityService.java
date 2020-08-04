package com.kh.banzi.community.model.service;

import static com.kh.banzi.common.DBCP.getConnection;

import java.io.File;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.kh.banzi.common.Attachment;
import com.kh.banzi.community.model.dao.CommunityDAO;
import com.kh.banzi.community.model.vo.Community;
import com.kh.banzi.community.model.vo.PageInfo;
import com.kh.banzi.community.model.vo.Reply;

/**
 * @author user1
 *
 */
/**
 * @author user1
 *
 */
public class CommunityService {

    private CommunityDAO dao;

    public CommunityService() throws Exception{
        dao = new CommunityDAO();
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

        return new PageInfo(cp, listCount, 3);
    }

    public List<Community> selectList(PageInfo pInfo) throws Exception {
        Connection conn = getConnection();

        List<Community> cList = dao.selectList(conn, pInfo);
        conn.close();

        return cList;
    }

    public Community selectCommunity(int boardNo) throws Exception{
        Connection conn = getConnection();

        Community community = dao.selectCommunity(conn, boardNo);

        if(community != null) {
            int result = dao.increaseView(conn, boardNo);

            if(result > 0) {
                conn.commit();
                community.setViews(community.getViews() + 1);
            }else
                conn.rollback();
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
        Connection conn = getConnection();

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

    /** 썸네일
     * @param pInfo
     * @return fList
     * @throws Exception
     */
    public List<Attachment> selectFileList(PageInfo pInfo) throws Exception{
        Connection conn = getConnection();
        List<Attachment> fList =dao.selectFileList(conn,pInfo);

        conn.close();
        
        return fList;
    }

    /** 게시글 파일 조회
     * @param boardNo
     * @return
     * @throws Exception
     */
    public List<Attachment> selectFiles(int boardNo, int boardType) throws Exception{
        Connection conn = getConnection();
        List<Attachment> fList = dao.selectFiles(conn, boardNo, boardType);
        conn.close();
        return fList;
    }

    /** 자유 게시판 삭제
     * @param boardNo
     * @return result
     * @throws Exception
     */
    public int deleteCommunity(int boardNo) throws Exception{
        Connection conn = getConnection();
        int result = dao.deleteCommunity(conn, boardNo);
        
        if(result > 0) 
            dao.deleteFiles(conn, boardNo, 3);
        
        if (result > 0)
            conn.commit();
        else
            conn.rollback();
        
        conn.close();
        
        return result;
    }

    /** 자유게시판 수정 View
     * @param boardNo
     * @return result
     * @throws Exception
     */
    public Community updateView(int boardNo) throws Exception{
        Connection conn = getConnection();
        Community community = dao.updateView(conn, boardNo);
        community.setContent(community.getContent().replaceAll("<br>", "\r\n")); 
        
        conn.close();
        return community;
    }

    /** 자유게시글 수정
     * @param community
     * @param fList
     * @return
     * @throws Exception
     */
    public int updateCommunity(Community community, List<Attachment> fList) throws Exception{
        Connection conn = getConnection();
        int result = 0;
        
        community.setContent(replaceParameter(community.getContent())); // 크로스 사이트 스크립팅 방지
        community.setContent(community.getContent().replace("\r\n", "<br>")); // 개행문자 처리
        
        result = dao.updateBoard(conn, community);
        
        List<Attachment> deleteFiles = new ArrayList<Attachment>();
        if(result>0 && !fList.isEmpty()) {
            result = 0; // result 재사용
            
            // 기존 해당 게시글에 포함되었던 파일 정보를 DB로 부터 얻어옴.
            List<Attachment> oldList = dao.selectFiles(conn, community.getBoardNo(), community.getBoardType());
            
            boolean flag = false; 
            for(Attachment newFile : fList) {
               
               flag = false; 
               
               for(Attachment oldFile : oldList) {
                  
                  if(newFile.getFileLevel() == oldFile.getFileLevel()) {
                     flag = true;
                     deleteFiles.add(oldFile); // 기존파일을 삭제 리스트에 추가
                     newFile.setFileNo(oldFile.getFileNo());
                  }
               }
               newFile.setParentBoardNo(community.getBoardNo()); 
               newFile.setParentBoardType(community.getBoardType());
               if(flag) { 
                  result = dao.updateAttachment(conn, newFile);
                  
               }else { 
                  result = dao.insertAttachment(conn, newFile);
               }
               
               if(result==0) break;
            }
         }
        List<Attachment> tempList = null;
        
        if(result>0) {
           result = community.getBoardNo();
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

    public int insertReply(Reply reply) throws Exception{
        Connection conn = getConnection();
        
        reply.setContent(replaceParameter(reply.getContent()));
        
        // 개행문자 처리 \r\n -> <br>
        reply.setContent(reply.getContent().replaceAll("\n", "<br>"));
        
        int result = dao.insertReply(conn, reply);
        
        if(result > 0)
            conn.commit();
        else
            conn.rollback();
        
        conn.close();
        return result;
    }

}
