package com.bootcamp.induk;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bootcamp.induk.dao.BoardDao;
import com.bootcamp.induk.domain.BoardDto;
import com.bootcamp.induk.domain.SearchCondition;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class BoardDaoImplTest {

	@Autowired
	private BoardDao boardDao;
	
	@Test
	public void searchSelectPageTest() throws Exception {
		boardDao.deleteAll();
		for(int i = 1; i <= 20; i++) {
			BoardDto boardDto = new BoardDto("title" + i, "qwertest" + i, "asdf" + i);
			boardDao.insert(boardDto);
		}
		
		SearchCondition sc = new SearchCondition(1, 10, "title2", "T");	// title2%
		List<BoardDto> list = boardDao.searchSelectPage(sc);
//		System.out.println("list = " + list);
		assertTrue(list.size() == 2);	// 1 ~ 20, title2, title20
		
		sc = new SearchCondition(1, 10, "asdf2", "W");	// asdf2%
		list = boardDao.searchSelectPage(sc);
		assertTrue(list.size() == 2);	// 1 ~ 20, asdf2, asdf20
	}
	
	@Test
	public void searchResultCntTest() throws Exception {
		boardDao.deleteAll();
		for(int i = 1; i <= 20; i++) {
			BoardDto boardDto = new BoardDto("title" + i, "asdfqwer" + i, "asdf" + i);
			boardDao.insert(boardDto);
		}
		
		SearchCondition sc = new SearchCondition(1, 10, "title2", "T");	// title2%
		int cnt = boardDao.searchResultCnt(sc);
		System.out.println("cnt = " + cnt);
		assertTrue(cnt == 2);
		
		sc = new SearchCondition(1, 10, "asdf2", "W");
		cnt = boardDao.searchResultCnt(sc);
		assertTrue(cnt == 2);
	}
	
	@Test
	public void insertTestData() throws Exception {
		boardDao.deleteAll();
		for(int i = 1; i <= 220; i++) {
			BoardDto boardDto = new BoardDto("title" + i, "no content", "asdf");
			boardDao.insert(boardDto);
		}
	}
	
	@Test
	public void countTest() throws Exception {
		boardDao.deleteAll();
		assertTrue(boardDao.count() == 0);
		
		BoardDto boardDto = new BoardDto("no title", "no content", "asdf");
		assertTrue(boardDao.insert(boardDto) == 1);
		assertTrue(boardDao.count() == 1);
		
		assertTrue(boardDao.insert(boardDto) == 1);
		assertTrue(boardDao.count() == 2);
	}
	
	@Test
	public void deleteAllTest() throws Exception {
		boardDao.deleteAll();
		assertTrue(boardDao.count() == 0);
		
		BoardDto boardDto = new BoardDto("no title", "no content", "asdf");
		assertTrue(boardDao.insert(boardDto) == 1);
		assertTrue(boardDao.deleteAll() == 1);
		assertTrue(boardDao.count() == 0);
		
		boardDto = new BoardDto("no title", "no content", "asdf");
		assertTrue(boardDao.insert(boardDto) == 1);
		assertTrue(boardDao.insert(boardDto) == 1);
		assertTrue(boardDao.deleteAll() == 2);
		assertTrue(boardDao.count() == 0);
	}
	
	@Test
	public void deleteTest() throws Exception {
		boardDao.deleteAll();
		assertTrue(boardDao.count() == 0);
		
		BoardDto boardDto = new BoardDto("no title", "no content", "asdf");
		assertTrue(boardDao.insert(boardDto) == 1);
		
		Integer bno = boardDao.selectAll().get(0).getBno();
		assertTrue(boardDao.delete(bno, boardDto.getWriter()) == 1);
		assertTrue(boardDao.count() == 0);
		
		assertTrue(boardDao.insert(boardDto) == 1);
		bno = boardDao.selectAll().get(0).getBno();
		assertTrue(boardDao.delete(bno, boardDto.getWriter() + "222") == 0);
		assertTrue(boardDao.count() == 1);
		
		assertTrue(boardDao.delete(bno, boardDto.getWriter()) == 1);
		assertTrue(boardDao.count() == 0);
		
		assertTrue(boardDao.insert(boardDto) == 1);
		bno = boardDao.selectAll().get(0).getBno();
		assertTrue(boardDao.delete(bno + 1, boardDto.getWriter()) == 0);
		assertTrue(boardDao.count() == 1);
	}
	
	@Test
	public void insertTest() throws Exception {
		boardDao.deleteAll();
		BoardDto boardDto = new BoardDto("no title", "no content", "asdf");
		assertTrue(boardDao.insert(boardDto) == 1);
		
		boardDto = new BoardDto("no title", "no content", "asdf");
		assertTrue(boardDao.insert(boardDto) == 1);
		assertTrue(boardDao.count() == 2);
		
		boardDao.deleteAll();
		boardDto = new BoardDto("no title", "no content", "asdf");
		assertTrue(boardDao.insert(boardDto) == 1);
		assertTrue(boardDao.count() == 1);
	}
	
	@Test
	public void selectAllTest() throws Exception {
		boardDao.deleteAll();
		assertTrue(boardDao.count() == 0);
		
		List<BoardDto> list = boardDao.selectAll();
		assertTrue(list.size() == 0);
		
		BoardDto boardDto = new BoardDto("no title", "no content", "asdf");
		assertTrue(boardDao.insert(boardDto) == 1);
		
		list = boardDao.selectAll();
		assertTrue(list.size() == 1);
		
		assertTrue(boardDao.insert(boardDto) == 1);
		list = boardDao.selectAll();
		assertTrue(list.size() == 2);
	}
	
	@Test
	public void selectTest() throws Exception {
		boardDao.deleteAll();
		assertTrue(boardDao.count() == 0);
		
		BoardDto boardDto = new BoardDto("no title", "no content", "asdf");
		assertTrue(boardDao.insert(boardDto) == 1);
		
		Integer bno = boardDao.selectAll().get(0).getBno();
		boardDto.setBno(bno);
		BoardDto boardDto2 = boardDao.select(bno);
		assertTrue(boardDto.equals(boardDto2));
	}
	
	@Test
	public void selectPageTest() throws Exception {
		boardDao.deleteAll();
		
		for(int i = 1; i <= 10; i++) {
			BoardDto boardDto = new BoardDto("" + i, "no content" + i, "asdf");
			boardDao.insert(boardDto);
		}
		
		Map map = new HashMap();
		map.put("offset", 0);
		map.put("pageSize", 3);
		
		List<BoardDto> list = boardDao.selectPage(map);
		assertTrue(list.get(0).getTitle().equals("10"));
		assertTrue(list.get(1).getTitle().equals("9"));
		assertTrue(list.get(2).getTitle().equals("8"));
		
		map = new HashMap();
		map.put("offset", 0);
		map.put("pageSize", 1);
		
		list = boardDao.selectPage(map);
		assertTrue(list.get(0).getTitle().equals("10"));
		
		map = new HashMap();
		map.put("offset", 7);
		map.put("pageSize", 3);
		
		list = boardDao.selectPage(map);
		assertTrue(list.get(0).getTitle().equals("3"));
		assertTrue(list.get(1).getTitle().equals("2"));
		assertTrue(list.get(2).getTitle().equals("1"));
	}
	
	@Test
	public void updateTest() throws Exception {
		boardDao.deleteAll();
		BoardDto boardDto = new BoardDto("no title", "no content", "asdf");
		assertTrue(boardDao.insert(boardDto) == 1);
		
		Integer bno = boardDao.selectAll().get(0).getBno();
		System.out.println("bno = " + bno);
		boardDto.setBno(bno);
		boardDto.setTitle("yes title");
		assertTrue(boardDao.update(boardDto) == 1);
		
		BoardDto boardDto2 = boardDao.select(bno);
		assertTrue(boardDto.equals(boardDto2));
	}
	
	@Test
	public void increaseViewCntTest() throws Exception {
		boardDao.deleteAll();
		assertTrue(boardDao.count() == 0);
		
		BoardDto boardDto = new BoardDto("no title", "no content", "asdf");
		assertTrue(boardDao.insert(boardDto) == 1);
		assertTrue(boardDao.count() == 1);
		
		Integer bno = boardDao.selectAll().get(0).getBno();
		assertTrue(boardDao.increaseViewCnt(bno) == 1);
		
		boardDto = boardDao.select(bno);
		assertTrue(boardDto != null);
		assertTrue(boardDto.getView_cnt() == 1);
		
		assertTrue(boardDao.increaseViewCnt(bno) == 1);
		boardDto = boardDao.select(bno);
		assertTrue(boardDto != null);
		assertTrue(boardDto.getView_cnt() == 2);
	}
	
	@Test
	public void select() throws Exception {
		boardDao.deleteAll();
		assertTrue(boardDao != null);
		System.out.println("boardDao = " + boardDao);
		BoardDto boardDto = boardDao.select(1);
		System.out.println("boardDto = " + boardDto);
		assertTrue(boardDto.getBno().equals(1));
	}
}
