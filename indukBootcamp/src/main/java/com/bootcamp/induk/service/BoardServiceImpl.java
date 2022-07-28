package com.bootcamp.induk.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.induk.dao.BoardDao;
import com.bootcamp.induk.domain.BoardDto;
import com.bootcamp.induk.domain.SearchCondition;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardDao boardDao;
	
	@Override
	public int getCount() throws Exception {
		return boardDao.count();
	}
	
	@Override
	public int remove(Integer bno, String writer) throws Exception {
		return boardDao.delete(bno, writer);
	}
	
	@Override
	public int write(BoardDto boardDto) throws Exception {
		// throw new Exception("test");
		return boardDao.insert(boardDto);
	}
	
	@Override
	public List<BoardDto> getList() throws Exception {
		return boardDao.selectAll();
	}
	
	@Override
	public BoardDto read(Integer bno) throws Exception {
		BoardDto boardDto = boardDao.select(bno);
		boardDao.increaseViewCnt(bno);
		
		return boardDto;
	}
	
	@Override
	public List<BoardDto> getPage(Map map) throws Exception {
		return boardDao.selectPage(map);
	}
	
	@Override
	public int modify(BoardDto boardDto) throws Exception {
		return boardDao.update(boardDto);
	}
	
	@Override
	public int getSearchResultCnt(SearchCondition sc) throws Exception {
		return boardDao.searchResultCnt(sc);
	}
	
	@Override
	public List<BoardDto> getSearchResultPage(SearchCondition sc) throws Exception {
		return boardDao.searchSelectPage(sc);
	}
}
