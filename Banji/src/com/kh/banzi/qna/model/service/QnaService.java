package com.kh.banzi.qna.model.service;

import static com.kh.banzi.common.DBCP.getConnection;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.kh.banzi.common.Attachment;
import com.kh.banzi.common.PageInfo;
import com.kh.banzi.community.model.dao.CommunityDAO;
import com.kh.banzi.community.model.service.CommunityService;
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

            qna.setContent(replaceParameter(qna.getContent()));
            qna.setContent(qna.getContent().replaceAll("\r\n", "<br>"));


            result = dao.insertQna(conn, qna);

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
        List<Attachment> fList = dao.selectFiles(conn, boardNo, 5);

        return fList;
    }

    public int inserReply(Reply reply) throws Exception{
        Connection conn= getConnection();
        
        reply.setContent(replaceParameter(reply.getContent()));
        
        reply.setContent(reply.getContent().replaceAll("\n", "<br>"));
        int result = dao.insertReply(conn, reply);

        if(result > 0)
            result = dao.increaseView(conn, reply);

        if (result > 0)
            conn.commit();
        else
            conn.rollback();
        conn.close();

        return result;
    }

    /** 게시글 수정 화면
     * @param boardNo
     * @return
     */
    public Qna updateView(int boardNo) throws Exception{
        Connection conn = getConnection();
        Qna qna = dao.updateView(conn, boardNo);
        qna.setContent(qna.getContent().replaceAll("<br>", "\r\n"));
        conn.close();
        return qna;
    }

    public int updateBoard(Qna qna, List<Attachment> fList) throws Exception{
        Connection conn = getConnection();
        int result = 0;

        // 크로스 사이트 스크립트 방지 + 개행문자 처리
        qna.setContent(replaceParameter(qna.getContent())); // 크로스 사이트 스크립팅 방지
        qna.setContent(qna.getContent().replace("\r\n", "<br>")); // 개행문자 처리

        // 게시글 수정 DAO 호출
        result = dao.updateBoard(conn, qna);

        List<Attachment> deleteFiles = new ArrayList<Attachment>();

        if(result>0 && !fList.isEmpty()) {
            result = 0; // result 재사용

            List<Attachment> oldList = dao.selectFiles(conn, qna.getBoardNo(), qna.getBoardType());

            boolean flag = false; // 결과확인용도 : 같을때 true
            for(Attachment newFile : fList) {

                flag = false; // flag 초기화

                for(Attachment oldFile : oldList) {

                    if(newFile.getFileLevel() == oldFile.getFileLevel()) {
                        flag = true;
                        deleteFiles.add(oldFile); // 기존파일을 삭제 리스트에 추가
                        newFile.setFileNo(oldFile.getFileNo());
                    }
                }
                newFile.setParentBoardType(qna.getBoardType());
                newFile.setParentBoardNo(qna.getBoardNo()); // 너가 작성되는 글이 나의 번호이다.

                if(flag) { // update 상황 (flag:true) == 겹칠때
                    result = dao.updateAttachment(conn, newFile);

                }else { // insert 상황 (새로운 이미지 추가 상황) == 겹치는게 없을때
                    result = dao.insertAttachment(conn, newFile);
                }

                if(result==0) break;
            }
        }

        // 트랜잭션 처리 + 삭제 처리

        List<Attachment> tempList = null;

        // service의 모든 동작이 성공적으로 진행된 경우
        // deleteFile에 담긴 기존 파일을 삭제해야되고

        // service 동작 중 오류 또는 실패 발생 시
        // fList에 담긴 새로운 파일을 삭제해야함.

        if(result>0) {
            result = qna.getBoardNo();
            // 수정 완료 후 해당 게시글 상세 보기를 위해 result에 글번호를 저장하여 반환
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

    /** 댓글 삭제
     * @param replyNo
     * @return result
     * @throws Exception
     */
    public int deleteReply(int replyNo, int boardNo) throws Exception{
        Connection conn = getConnection();
        
        int result = dao.deleteReply(conn, replyNo);
        
        
        if (result > 0) {
            dao.decreaseReply(conn, boardNo);
            conn.commit();
        }
        else
            conn.rollback();
        conn.close();
        return result;
    }

    public int deleteQna(int boardNo) throws Exception{
        Connection conn = getConnection();
        
        int result = dao.deleteQna(conn, boardNo);
        
        if(result > 0) {
            new CommunityDAO().deleteFiles(conn, boardNo, 5);
            conn.commit();
        }
        else
            conn.rollback();
        conn.close();
        return result;
    }


}
