package com.bootcamp.induk.mapper;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bootcamp.induk.domain.BoardDto;
import com.bootcamp.induk.domain.SearchCondition;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class BoardMapperTest {


	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	public void searchSelectPageTest() throws Exception {
		boardMapper.deleteBoardList();
		for(int i = 1; i <= 20; i++) {
			BoardDto boardDto = new BoardDto("title" + i, "qwertest" + i, "asdf" + i);
			boardMapper.insertBoard(boardDto);
		}
		
		SearchCondition sc = new SearchCondition(1, 20, "title2", "T");	// title2%
		List<BoardDto> list = boardMapper.searchSelectPage(sc);
		System.out.println("list = " + list);
		assertTrue(list.size() == 2);	// 1 ~ 20, title2, title20
		
		sc = new SearchCondition(1, 20, "asdf2", "W");	// asdf2%
		list = boardMapper.searchSelectPage(sc);
		assertTrue(list.size() == 2);	// 1 ~ 20, asdf2, asdf20
	}
	
	@Test
	public void searchResultCntTest() throws Exception {
		boardMapper.deleteBoardList();
		for(int i = 1; i <= 20; i++) {
			BoardDto boardDto = new BoardDto("title" + i, "asdfqwer" + i, "asdf" + i);
			boardMapper.insertBoard(boardDto);
		}
		
		SearchCondition sc = new SearchCondition(1, 10, "title2", "T");	// title2%
		int cnt = boardMapper.searchResultCnt(sc);
		System.out.println("cnt = " + cnt);
		assertTrue(cnt == 2);
		
		sc = new SearchCondition(1, 10, "asdf2", "W");
		cnt = boardMapper.searchResultCnt(sc);
		assertTrue(cnt == 2);
	}
	
	@Test
	public void insertTestData() throws Exception {
		boardMapper.deleteBoardList();
		for(int i = 1; i <= 200; i++) {
			BoardDto boardDto = new BoardDto("title" + i, "no content" + i, "asdf");
			boardMapper.insertBoard(boardDto);
		}
	}
	
	@Test
	public void countTest() throws Exception {
		boardMapper.deleteBoardList();
		assertTrue(boardMapper.getCount() == 0);
		
		BoardDto boardDto = new BoardDto("no title", "no content", "asdf");
		assertTrue(boardMapper.insertBoard(boardDto) == 1);
		assertTrue(boardMapper.getCount() == 1);
		
		assertTrue(boardMapper.insertBoard(boardDto) == 1);
		assertTrue(boardMapper.getCount() == 2);
	}
	
	@Test
	public void deleteBoardListTest() throws Exception {
		boardMapper.deleteBoardList();
		assertTrue(boardMapper.getCount() == 0);
		
		BoardDto boardDto = new BoardDto("no title", "no content", "asdf");
		assertTrue(boardMapper.insertBoard(boardDto) == 1);
		assertTrue(boardMapper.deleteBoardList() == 1);
		assertTrue(boardMapper.getCount() == 0);
		
		boardDto = new BoardDto("no title", "no content", "asdf");
		assertTrue(boardMapper.insertBoard(boardDto) == 1);
		assertTrue(boardMapper.insertBoard(boardDto) == 1);
		assertTrue(boardMapper.deleteBoardList() == 2);
		assertTrue(boardMapper.getCount() == 0);
	}
	
	@Test
	public void deleteBoardTest() throws Exception {
		boardMapper.deleteBoardList();
		assertTrue(boardMapper.getCount() == 0);
		
		BoardDto boardDto = new BoardDto("no title", "no content", "asdf");
		assertTrue(boardMapper.insertBoard(boardDto) == 1);
		
		Integer bno = boardMapper.selectBoardList().get(0).getBno();
		assertTrue(boardMapper.deleteBoard(bno, boardDto.getWriter()) == 1);
		assertTrue(boardMapper.getCount() == 0);
		
		assertTrue(boardMapper.insertBoard(boardDto) == 1);
		bno = boardMapper.selectBoardList().get(0).getBno();
		assertTrue(boardMapper.deleteBoard(bno, boardDto.getWriter() + "222") == 0);
		assertTrue(boardMapper.getCount() == 1);
		
		assertTrue(boardMapper.deleteBoard(bno, boardDto.getWriter()) == 1);
		assertTrue(boardMapper.getCount() == 0);
		
		assertTrue(boardMapper.insertBoard(boardDto) == 1);
		bno = boardMapper.selectBoardList().get(0).getBno();
		assertTrue(boardMapper.deleteBoard(bno + 1, boardDto.getWriter()) == 0);
		assertTrue(boardMapper.getCount() == 1);
	}
	
	@Test
	public void insertBoardTest() throws Exception {
		boardMapper.deleteBoardList();
		BoardDto boardDto = new BoardDto("no title", "no content", "asdf");
		assertTrue(boardMapper.insertBoard(boardDto) == 1);
		
		boardDto = new BoardDto("no title", "no content", "asdf");
		assertTrue(boardMapper.insertBoard(boardDto) == 1);
		assertTrue(boardMapper.getCount() == 2);
		
		boardMapper.deleteBoardList();
		boardDto = new BoardDto("no title", "no content", "asdf");
		assertTrue(boardMapper.insertBoard(boardDto) == 1);
		assertTrue(boardMapper.getCount() == 1);
	}
	
	@Test
	public void selectBoardListTest() throws Exception {
		boardMapper.deleteBoardList();
		assertTrue(boardMapper.getCount() == 0);
		
		List<BoardDto> list = boardMapper.selectBoardList();
		assertTrue(list.size() == 0);
		
		BoardDto boardDto = new BoardDto("no title", "no content", "asdf");
		assertTrue(boardMapper.insertBoard(boardDto) == 1);
		
		list = boardMapper.selectBoardList();
		assertTrue(list.size() == 1);
		
		assertTrue(boardMapper.insertBoard(boardDto) == 1);
		list = boardMapper.selectBoardList();
		assertTrue(list.size() == 2);
	}
	
	@Test
	public void selectTest() throws Exception {
		boardMapper.deleteBoardList();
		assertTrue(boardMapper.getCount() == 0);
		BoardDto boardDto = new BoardDto("no title", "no content", "asdf");
		assertTrue(boardMapper.insertBoard(boardDto) == 1);

		Integer bno = boardMapper.selectBoardList().get(0).getBno();
		System.out.println(boardMapper.selectBoardList());
		boardDto.setBno(bno);
		BoardDto boardDto2 = boardMapper.selectBoard(bno);
		assertThat(boardDto.equals(boardDto2));
	}
	
	@Test
	public void selectPageTest() throws Exception {
		boardMapper.deleteBoardList();
		
		for(int i = 1; i <= 10; i++) {
			BoardDto boardDto = new BoardDto("" + i, "no content" + i, "asdf");
			boardMapper.insertBoard(boardDto);
		}
		
		Map map = new HashMap();
		map.put("page", 1);
		map.put("pageSize", 3);
		
		List<BoardDto> list = boardMapper.selectPage(map);
		assertTrue(list.get(0).getTitle().equals("10"));
		assertTrue(list.get(1).getTitle().equals("9"));
		assertTrue(list.get(2).getTitle().equals("8"));
		
		map = new HashMap();
		map.put("page", 1);
		map.put("pageSize", 1);
		
		list = boardMapper.selectPage(map);
		assertTrue(list.get(0).getTitle().equals("10"));
		
		map = new HashMap();
		map.put("page", 1);
		map.put("pageSize", 10);
		
		list = boardMapper.selectPage(map);
		System.out.println(list.get(0).getTitle());
		System.out.println(list.get(1).getTitle());
		System.out.println(list.get(2).getTitle());
		System.out.println(list.get(3).getTitle());
		System.out.println(list.get(4).getTitle()); // 6
 		System.out.println(list.get(5).getTitle()); // 5
		System.out.println(list.get(6).getTitle()); // 4
		System.out.println(list.get(7).getTitle()); // 3
		
		assertTrue(list.get(7).getTitle().equals("3"));
		assertTrue(list.get(8).getTitle().equals("2"));
		assertTrue(list.get(9).getTitle().equals("1"));
	}
	
	@Test
	@Transactional
	public void updateTest() throws Exception {
		boardMapper.deleteBoardList();
		BoardDto boardDto = new BoardDto("no title", "no content", "asdf");
		assertTrue(boardMapper.insertBoard(boardDto) == 1);
		System.out.println(boardDto);
		Integer bno = boardMapper.selectBoardList().get(0).getBno();
		System.out.println("bno = " + bno);
		boardDto.setBno(bno);
		boardDto.setTitle("yes title");
		assertTrue(boardMapper.updateBoard(boardDto) == 1);
		System.out.println(boardDto);
		System.out.println("bno = " + bno);
		BoardDto boardDto2 = boardMapper.selectBoard(bno);
		assertThat(boardDto.equals(boardDto2));
	}
	
	@Test
	public void increaseViewCntTest() throws Exception {
		boardMapper.deleteBoardList();
		assertTrue(boardMapper.getCount() == 0);
		
		BoardDto boardDto = new BoardDto("no title", "no content", "asdf");
		assertTrue(boardMapper.insertBoard(boardDto) == 1);
		assertTrue(boardMapper.getCount() == 1);
		
		Integer bno = boardMapper.selectBoardList().get(0).getBno();
		assertTrue(boardMapper.increaseViewCnt(bno) == 1);
		
		boardDto = boardMapper.selectBoard(bno);
		assertTrue(boardDto != null);
		System.out.println("boardDto.getView_cnt() = " + boardDto.getView_cnt());
		assertTrue(boardDto.getView_cnt() == 1);
		
		assertTrue(boardMapper.increaseViewCnt(bno) == 1);
		boardDto = boardMapper.selectBoard(bno);
		assertTrue(boardDto != null);
		assertTrue(boardDto.getView_cnt() == 2);
	}
	
}
