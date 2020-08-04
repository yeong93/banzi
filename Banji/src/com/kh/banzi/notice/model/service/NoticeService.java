package com.kh.banzi.notice.model.service;

import static com.kh.banzi.common.DBCP.getConnection;
import java.sql.Connection;
import java.util.List;

import com.kh.banzi.common.Attachment;
import com.kh.banzi.common.PageInfo;
import com.kh.banzi.notice.model.dao.NoticeDAO;
import com.kh.banzi.notice.model.vo.Notice;
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
    
}
