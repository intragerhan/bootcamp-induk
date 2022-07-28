package com.bootcamp.induk.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.induk.dao.BoardDao;
import com.bootcamp.induk.domain.BoardDto;
import com.bootcamp.induk.domain.SearchCondition;

public interface BoardService {
	int getCount() throws Exception;
	int remove(Integer bno, String writer) throws Exception;
	int write(BoardDto boardDto) throws Exception;
	List<BoardDto> getList() throws Exception;
	BoardDto read(Integer bno) throws Exception;
	List<BoardDto> getPage(Map map) throws Exception;
	int modify(BoardDto boardDto) throws Exception;
	
	int getSearchResultCnt(SearchCondition sc) throws Exception;
	List<BoardDto> getSearchResultPage(SearchCondition sc) throws Exception;
}
