package com.kh.banzi.notice.model.service;

import static com.kh.banzi.common.DBCP.getConnection;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.kh.banzi.common.Attachment;
import com.kh.banzi.common.PageInfo;
import com.kh.banzi.community.model.dao.CommunityDAO;
import com.kh.banzi.notice.model.dao.NoticeDAO;
import com.kh.banzi.notice.model.vo.Notice;
import com.kh.banzi.qna.model.dao.QnaDAO;
import com.kh.banzi.qna.model.service.QnaService;

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

    public int updateNotice(Notice notice, List<Attachment> fList) throws Exception{
        Connection conn = getConnection();
        int result = 0;
        notice.setContent(replaceParameter(notice.getContent())); // 크로스 사이트 스크립팅 방지
        notice.setContent(notice.getContent().replace("\r\n", "<br>")); // 개행문자 처리

        result = dao.updateNotice(conn, notice);


        List<Attachment> deleteFiles = new ArrayList<Attachment>();

        if(result>0 && !fList.isEmpty()) {
            result = 0; // result 재사용

            List<Attachment> oldList = new QnaDAO().selectFiles(conn, notice.getBoardNo(), notice.getBoardType());

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
                newFile.setParentBoardNo(notice.getBoardNo()); // 너가 작성되는 글이 나의 번호이다.
                newFile.setParentBoardType(notice.getBoardType());

                if(flag) { // update 상황 (flag:true) == 겹칠때
                    result = new QnaDAO().updateAttachment(conn, newFile);

                }else { // insert 상황 (새로운 이미지 추가 상황) == 겹치는게 없을때
                    result = new QnaDAO().insertAttachment(conn, newFile);
                }

                if(result==0) break;
            }
        }


        List<Attachment> tempList = null;

        if(result>0) {
            result = notice.getBoardNo();
            conn.commit();
            tempList = deleteFiles;
        }else {
            conn.rollback();
            tempList = fList;
        }

        for(Attachment at : tempList) {
            String filePath = at.getFilePath();
            String fileName = at.getFileChangeName();

            File deleteFile = new File(filePath + fileName);
            deleteFile.delete();
        }

        conn.close();
        return result;
    }

    public Notice updateView(int boardNo) throws Exception{
        Connection conn = getConnection();
        Notice notice = dao.udpateView(conn, boardNo);
        notice.setContent(notice.getContent().replaceAll("<br>", "\r\n"));
        conn.close();
        return notice;
    }
}

