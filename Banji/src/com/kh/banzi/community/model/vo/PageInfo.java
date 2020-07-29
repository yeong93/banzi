package com.kh.banzi.community.model.vo;

public class PageInfo {
    
    private int currentPage;        // 현재 페이지 번호
    private int listCount;          // 전체 게시글 수
    private int limit = 10;              // 한 페이지에 보여질 게시글의 수
    private int pagingBarSize = 10;     // 화면에 표시될 페이징바의 페이지 수
    
    private int maxPage;            // 전체 페이지 중 제일 마지막 페이지
    private int startPage;           // 페이징바 시작 페이지 번호
    private int endPage;            // 페이징바 마지막 페이지 번호
    
    private int boardType;          // 게시글 타입
    
    

    
    public PageInfo(int currentPage, int listCount) {
        super();
        this.currentPage = currentPage;
        this.listCount = listCount;
        makePageInfo();

    }

    public PageInfo(int currentPage, int listCount, int boardType) {
        super();
        this.currentPage = currentPage;
        this.listCount = listCount;
        this.boardType = boardType;
        makePageInfo();

    }

    public PageInfo(int currentPage, int listCount, int limit, int pageingBarSize, int boardType) {
        super();
        this.currentPage = currentPage;
        this.listCount = listCount;
        this.limit = limit;
        this.pagingBarSize = pageingBarSize;
        this.boardType = boardType;
        
        makePageInfo();
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        makePageInfo();
    }

    public int getListCount() {
        return listCount;
    }

    public void setListCount(int listCount) {
        this.listCount = listCount;
        makePageInfo();

    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
        makePageInfo();

    }

    public int getPageingBarSize() {
        return pagingBarSize;
    }

    public void setPageingBarSize(int pageingBarSize) {
        this.pagingBarSize = pageingBarSize;
        makePageInfo();

    }

    public int getMaxPage() {
        return maxPage;
    }


    public int getStartPage() {
        return startPage;
    }


    public int getEndPage() {
        return endPage;
    }


    public int getBoardType() {
        return boardType;
    }

    public void setBoardType(int boardType) {
        this.boardType = boardType;
    }

    @Override
    public String toString() {
        return "PageInfo [currentPage=" + currentPage + ", listCount=" + listCount + ", limit=" + limit
                + ", pageingBarSize=" + pagingBarSize + ", maxPage=" + maxPage + ", statPage=" + startPage
                + ", endPage=" + endPage + ", boardType=" + boardType + "]";
    }
    
    
    // 페이징 처리에 필요한 값을 계산하는 메소드
    private void makePageInfo() {
        
        // maxPage : 가장 마지막 == 총 페이지 수
        // limit 가 10일 때
        // 게시글이 100개일 경우 필요한 페이지 수 ? 10
        // 게시글이 101개일 경우 필요한 페이지 수 ? 11
        
        maxPage = (int)Math.ceil((double)listCount / pagingBarSize); 
        
        // startPage : 페이징바 시작 페이지 번호
        // 페이징바의 크기가 10인 경우 시작 페이지 번호는
        
        startPage = (currentPage - 1)/ pagingBarSize * pagingBarSize + 1;
        
        // endPage : 페이징 바 끝 페이지 번호
        
        // endPage가 maxPage보다 클 경우
        if(endPage > maxPage)
            endPage = maxPage;
        else
            endPage = startPage + pagingBarSize - 1;
            
    }
    
    
}