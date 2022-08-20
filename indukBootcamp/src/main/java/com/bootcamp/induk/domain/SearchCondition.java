package com.bootcamp.induk.domain;

import org.springframework.web.util.UriComponentsBuilder;

import static java.lang.Math.*;

public class SearchCondition {
	private Integer page = 1;
	private Integer pageSize = DEFAULT_PAGE_SIZE;
//	private Integer offset = 0;
	private String keyword = "";
	private String option = "";	
	
    public static final int MIN_PAGE_SIZE = 5;
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int MAX_PAGE_SIZE = 50;
	
	public SearchCondition() {}
    public SearchCondition(Integer page, Integer pageSize) {
        this(page, pageSize, "", "");
    }
	public SearchCondition(Integer page, Integer pageSize, String keyword, String option) {
		this.page = page;
		this.pageSize = pageSize;
		this.keyword = keyword;
		this.option = option;
	}
	  
	public String getQueryString(Integer page) {
		// ?page=1&pageSize=10&option=T&keyword="title"
		return UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("pageSize", pageSize)
				.queryParam("option", option)
				.queryParam("keyword", keyword)
				.build().toString();
	}
	
	public String getReplyQueryString() {
		return getReplyQueryString(page);
	}
	
	public String getReplyQueryString(Integer page) {
		// ?page=1&pageSize=10&option=T&keyword="title"
		return UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("pageSize", pageSize)
				.build().toString();
	}
	
	public String getQueryString() {
		return getQueryString(page);
	}
	
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
		
		// MIN_PAGE_SIZE <= pageSize <= MAX_PAGE_SIZE
		this.pageSize = max(MIN_PAGE_SIZE, min(this.pageSize, MAX_PAGE_SIZE));
	}

	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}

	public Integer getOffset() {
		return (page - 1) * pageSize;
	}
	
	@Override
	public String toString() {
		return "SearchCondition ["
				+ "page=" + page 
				+ ", pageSize=" + pageSize 
				+ ", offset=" + getOffset() 
				+ ", keyword=" + keyword 
				+ ", option=" + option 
				+ ']';
	}
	
	
}
