package com.kh.banzi.notice.model.service;

import static com.kh.banzi.common.DBCP.getConnection;

import java.io.File;
import java.sql.Connection;
import java.util.List;

import com.kh.banzi.common.Attachment;
import com.kh.banzi.common.PageInfo;
import com.kh.banzi.community.model.dao.CommunityDAO;
import com.kh.banzi.notice.model.dao.NoticeDAO;
import com.kh.banzi.notice.model.vo.Notice;
import com.kh.banzi.qna.model.dao.QnaDAO;
import com.kh.banzi.qna.model.service.QnaService;
import com.kh.banzi.qna.model.vo.Qna;

public class NoticeService {
    private NoticeDAO dao;
    
    public NoticeService() throws Exception{
        dao = new NoticeDAO();
    }

    public List<Notice> selectList(PageInfo pInfo) throws Exception{
        Connection conn = getConnection();

        List<Notice> nList = dao.selectList(conn, pInfo);
        conn.close();
        
        return nList;
    }

    public Notice selectNotice(int boardNo) throws Exception{
        Connection conn = getConnection();
        Notice notice =dao.selectNotice(conn, boardNo);
        
        if(notice != null) {
            int result = dao.increaseView(conn, boardNo);
            
            if(result > 0) {
                conn.commit();
                notice.setViews(notice.getViews() + 1);
            }else
                conn.rollback();
        }
        conn.close();
        return notice;
    }

    public int insertNotice(Notice notice, List<Attachment> fList) throws Exception{
        Connection conn = getConnection();
        
        int result = 0;
        
        int boardNo = new QnaDAO().selecNextNo(conn);
        if (boardNo > 0) {
            notice.setBoardNo(boardNo);
            System.out.println(boardNo);

            notice.setContent(replaceParameter(notice.getContent()));
            notice.setContent(notice.getContent().replaceAll("\r\n", "<br>"));


            result = dao.inserNotice(conn, notice);
            System.out.println(result);

            if(result > 0 && !fList.isEmpty()) {
                result = 0;

                for(Attachment at : fList) {

                    at.setParentBoardNo(boardNo);
                    at.setParentBoardType(notice.getBoardType());

                    result = new QnaDAO().insertAttachment(conn, at);

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

    public int deleteNotice(int boardNo) throws Exception{
        Connection conn = getConnection();
        int result = dao.deleteNotice(conn, boardNo);
        
        
        if(result > 0) {
            conn.commit();
            new CommunityDAO().deleteFiles(conn, boardNo, 4);
        }
        else
            conn.rollback();
        conn.close();
        return result;
    }

    
}
