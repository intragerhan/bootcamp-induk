package com.bootcamp.induk.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootcamp.induk.mapper.BoardMapper;
import com.bootcamp.induk.service.interfaces.BoardService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.bootcamp.induk.domain.BoardDto;
import com.bootcamp.induk.domain.SearchCondition;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	private final SqlSession session;

	private static String namespace = "com.bootcamp.induk.mapper.BoardMapper.";

	@Override
	public int writeBoard(BoardDto boardDto) throws Exception {
		return session.insert(namespace  + "insertBoard", boardDto);
	}

	@Override
	public List<BoardDto> getBoardList() throws Exception {
		return session.selectList(namespace + "selectBoardList");
	}

	@Override
	@Transactional
	public BoardDto readBoard(Integer bno) throws Exception {
		BoardDto boardDto = session.selectOne(namespace + "selectBoard", bno);
		session.update(namespace + "increaseViewCnt", bno);
		return boardDto;
	}

	@Override
	public int modifyBoard(BoardDto boardDto) throws Exception {
		return session.update(namespace + "updateBoard", boardDto);
	}

	@Override
	public int removeBoard(Integer bno, String writer) throws Exception {
		Map map = new HashMap();
		map.put("bno", bno);
		map.put("writer", writer);
		return session.delete(namespace + "deleteBoard", map);
	}

	@Override
	public int removeBoardList() throws Exception {
		return session.delete(namespace + "deleteBoardList");
	}

	@Override
	public int getCount() throws Exception {
		return session.selectOne(namespace + "getCount");
	}

	@Override
	public List<BoardDto> getPage(Map map) throws Exception {
		return session.selectList(namespace + "selectPage", map);
	}

	@Override
	public int getSearchResultCnt(SearchCondition sc) throws Exception {
		return session.selectOne(namespace + "searchResultCnt", sc);
	}
	
	@Override
	public List<BoardDto> getSearchResultPage(SearchCondition sc) throws Exception {
		return session.selectList(namespace + "searchSelectPage", sc);
	}
}
