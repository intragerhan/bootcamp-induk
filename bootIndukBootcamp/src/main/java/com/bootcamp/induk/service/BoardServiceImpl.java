package com.bootcamp.induk.service;

import java.util.List;
import java.util.Map;

import com.bootcamp.induk.mapper.BoardMapper;
import com.bootcamp.induk.service.interfaces.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.bootcamp.induk.domain.BoardDto;
import com.bootcamp.induk.domain.SearchCondition;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	private final BoardMapper boardMapper;

	@Override
	public int writeBoard(BoardDto boardDto) throws Exception {
		return boardMapper.insertBoard(boardDto);
	}

	@Override
	public List<BoardDto> getBoardList() throws Exception {
		return boardMapper.selectBoardList();
	}

	@Override
	public BoardDto readBoard(Integer bno) throws Exception {
		BoardDto boardDto = boardMapper.selectBoard(bno);
		boardMapper.increaseViewCnt(bno);
		return boardDto;
	}

	@Override
	public int modifyBoard(BoardDto boardDto) throws Exception {
		return boardMapper.updateBoard(boardDto);
	}

	@Override
	public int removeBoard(Integer bno, String writer) throws Exception {
		return boardMapper.deleteBoard(bno, writer);
	}

	@Override
	public int getCount() throws Exception {
		return boardMapper.getCount();
	}

	@Override
	public List<BoardDto> getPage(Map map) throws Exception {
		return boardMapper.selectPage(map);
	}

	@Override
	public int getSearchResultCnt(SearchCondition sc) throws Exception {
		return boardMapper.searchResultCnt(sc);
	}
	
	@Override
	public List<BoardDto> getSearchResultPage(SearchCondition sc) throws Exception {
		return boardMapper.searchSelectPage(sc);
	}
}
