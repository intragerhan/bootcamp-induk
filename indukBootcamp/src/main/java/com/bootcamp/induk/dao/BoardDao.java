package com.bootcamp.induk.dao;

import java.util.List;
import java.util.Map;

import com.bootcamp.induk.domain.BoardDto;
import com.bootcamp.induk.domain.SearchCondition;

public interface BoardDao {
	BoardDto select(Integer bno) throws Exception;
	int delete(Integer bno, String writer) throws Exception;
	int insert(BoardDto dto) throws Exception;
	int update(BoardDto dto) throws Exception;
	int increaseViewCnt(Integer bno) throws Exception;
	
	List<BoardDto> selectPage(Map map) throws Exception;
	List<BoardDto> selectAll() throws Exception;
	int deleteAll() throws Exception;
	int count() throws Exception;
	
	int searchResultCnt(SearchCondition sc) throws Exception;
	List<BoardDto> searchSelectPage(SearchCondition sc) throws Exception;
	
	int updateReplyCnt(Integer bno, int cnt);
}