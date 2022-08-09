package com.bootcamp.induk.service.interfaces;

import java.util.List;
import java.util.Map;

import com.bootcamp.induk.domain.BoardDto;
import com.bootcamp.induk.domain.SearchCondition;

public interface BoardService {

	int writeBoard(BoardDto boardDto) throws Exception;
	List<BoardDto> getBoardList() throws Exception;
	BoardDto readBoard(Integer bno) throws Exception;
	int modifyBoard(BoardDto boardDto) throws Exception;
	int removeBoard(Integer bno, String writer) throws Exception;

	int getCount() throws Exception;
	List<BoardDto> getPage(Map map) throws Exception;

	int getSearchResultCnt(SearchCondition sc) throws Exception;
	List<BoardDto> getSearchResultPage(SearchCondition sc) throws Exception;
}
