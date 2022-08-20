package com.bootcamp.induk.domain;

import org.springframework.web.util.UriComponentsBuilder;

public class PageHandler {
//	private int page;	// 현재 페이지
//	private int pageSize;	// 한 페이지의 크기
//	private String option;
//	private String keyword;
	private SearchCondition sc;

	public final int NAV_SIZE = 10;	// 페이지 네비게이션의 크기
	private int totalCnt; // 총 게시물 개수
	private int totalPage;	// 전체 페이지의 수
	private int beginPage;	// 네비게이션의 첫번째 페이지
	private int endPage;	// 네비게이션의 마지막 페이지
	private boolean showPrev = false;	// 이전 페이지로 이동하는 링크를 보여줄 것인지의 여부. beginPage == 1 이 아니면, showPrev는 false
	private boolean showNext = false;	// 다음 페이지로 이동하는 링크를 보여줄 것인지의 여부. endPage == totalPage이면, showNext는 false

	
    public PageHandler(int totalCnt, Integer page) {
        this(totalCnt, new SearchCondition(page, 10));
    }

    public PageHandler(int totalCnt, Integer page, Integer pageSize) {
        this(totalCnt, new SearchCondition(page, pageSize));
    }
	
	public PageHandler(int totalCnt, SearchCondition sc) {
		this.totalCnt = totalCnt;
		this.sc = sc;
		
		doPaging(totalCnt, sc);
	}
	
	public void doPaging(int totalCnt, SearchCondition sc) {
//		this.totalCnt = totalCnt / sc.getPageSize() + (totalCnt % sc.getPageSize() == 0 ? 0 : 1);
//		this.totalPage = (int)Math.ceil(totalCnt / (double)sc.getPageSize());
		this.totalPage = totalCnt / sc.getPageSize() + (totalCnt % sc.getPageSize() == 0 ? 0 : 1);
		this.sc.setPage(Math.min(sc.getPage(), totalPage));	// page가 totalPage보다 크지 않게
		this.beginPage = (this.sc.getPage() - 1) / NAV_SIZE * NAV_SIZE + 1;	// 11 -> 11, 10 -> 1, 15 -> 11
		this.endPage = Math.min(beginPage + NAV_SIZE - 1, totalPage);
		
		this.showPrev = beginPage != 1;
		this.showNext = endPage != totalPage;
	}
	

	public SearchCondition getSc() {
		return sc;
	}
	public void setSc(SearchCondition sc) {
		this.sc = sc;
	}
	
	public int getTotalCnt() {
		return totalCnt;
	}
	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}
	
	public int getNAV_SIZE() {
		return NAV_SIZE;
	}
	
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getBeginPage() {
		return beginPage;
	}
	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}
	
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
	public boolean isShowPrev() {
		return showPrev;
	}
	public void setShowPrev(boolean showPrev) {
		this.showPrev = showPrev;
	}
	
	public boolean isShowNext() {
		return showNext;
	}
	public void setShowNext(boolean showNext) {
		this.showNext = showNext;
	}
	
    public String getQueryString() {
        return getQueryString(this.sc.getPage());
    }

    public String getQueryString(Integer page) {
        // ?page=10&pageSize=10&option=A&keyword=title
        return UriComponentsBuilder.newInstance()
                .queryParam("page",     page)
                .queryParam("pageSize", sc.getPageSize())
                .queryParam("option",   sc.getOption())
                .queryParam("keyword",  sc.getKeyword())
                .build().toString();
    }
	
	public void print() {
		System.out.println("page = " + sc.getPage());
		System.out.print(showPrev ? "[PREV] " : "");
		for(int i = beginPage; i <= endPage; i++) {
			System.out.print(i + " ");
		}
		System.out.println(showNext ? " [NEXT]" : "");
	}

	@Override
	public String toString() {
		return "PageHandler "
				+ "[sc=" + sc 
				+ ", totalCnt=" + totalCnt 
				+ ", naviSize=" + NAV_SIZE 
				+ ", totalPage=" + totalPage 
				+ ", beginPage=" + beginPage 
				+ ", endPage=" + endPage 
				+ ", showPrev=" + showPrev
				+ ", showNext=" + showNext 
				+ ']';
	}
	
	
	
}
