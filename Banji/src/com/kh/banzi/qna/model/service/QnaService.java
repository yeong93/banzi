package com.kh.banzi.qna.model.service;

import static com.kh.banzi.common.DBCP.getConnection;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.kh.banzi.common.Attachment;
import com.kh.banzi.common.PageInfo;
import com.kh.banzi.community.model.vo.Reply;
import com.kh.banzi.qna.model.dao.QnaDAO;
import com.kh.banzi.qna.model.vo.Qna;

public class QnaService {

    private QnaDAO dao;

    public QnaService() throws Exception{
        dao = new QnaDAO();
    }

    public PageInfo getPageInfo(String currentPage) throws Exception{
        Connection conn = getConnection();

        // currentPage가 null인 경우 1, 아닌 경우 정수형으로 파싱
        int cp = (currentPage == null) ? 1 : Integer.parseInt(currentPage);

        // DB에서 전체 게시글 수 조회
        int listCount = dao.getListCount(conn);

        conn.close();

        return new PageInfo(cp, listCount, 5);
    }

    public List<Qna> selectList(PageInfo pInfo) throws Exception{
        Connection conn = getConnection();
        
        List<Qna> qList = dao.selectList(conn, pInfo);
        conn.close();
        return qList;
    }

    public Qna selectQna(int boardNo) throws Exception{
        Connection conn = getConnection();
        
        Qna qna = dao.selectQna(conn, boardNo);
        
        conn.close();
        return qna;
    }

    public List<Reply> selectReply(int boardNo) throws Exception{
        Connection conn = getConnection();
        
        List<Reply> rList = dao.selectReply(conn, boardNo);
        
        conn.close();
        return rList;
    }

    /** QNA 등록
     * @param qna
     * @param fList
     * @return result
     */
    public int insertQna(Qna qna, List<Attachment> fList) throws Exception{
        Connection conn = getConnection();
        
        int result = 0;
        
        int boardNo = dao.selecNextNo(conn);
        if (boardNo > 0) {
            qna.setBoardNo(boardNo);
            System.out.println(boardNo);

            qna.setContent(replaceParameter(qna.getContent()));
            qna.setContent(qna.getContent().replaceAll("\r\n", "<br>"));


            result = dao.insertQna(conn, qna);
            System.out.println(result);

            if(result > 0 && !fList.isEmpty()) {
                result = 0;

                for(Attachment at : fList) {

                    at.setParentBoardNo(boardNo);
                    at.setParentBoardType(qna.getBoardType());
                    
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

    public List<Attachment> selectFiles(int boardNo) throws Exception{
        Connection conn = getConnection();
        List<Attachment> fList = dao.selectFiles(conn, boardNo);
        
        return fList;
    }


}
