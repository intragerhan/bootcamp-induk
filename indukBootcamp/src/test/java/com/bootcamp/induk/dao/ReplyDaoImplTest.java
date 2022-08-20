package com.bootcamp.induk.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bootcamp.induk.dao.ReplyDao;
import com.bootcamp.induk.domain.ReplyDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ReplyDaoImplTest {
	@Autowired
	ReplyDao replyDao;

	@Test
	public void count() throws Exception {
		replyDao.deleteAll(1);
		assertTrue(replyDao.count(1) == 0);
	}
	
	@Test
	public void delete() throws Exception {
		replyDao.deleteAll(1);
		ReplyDto replyDto = new ReplyDto(1, 0, "reply", "asdf");
		assertTrue(replyDao.insert(replyDto) == 1);
		assertTrue(replyDao.count(1) == 1);
	}
	
	@Test
	public void insert() throws Exception {
		replyDao.deleteAll(1);
		ReplyDto replyDto = new ReplyDto(1, 0, "reply", "asdf");
		assertTrue(replyDao.insert(replyDto) == 1);
		assertTrue(replyDao.count(1) == 1);
		
		replyDto = new ReplyDto(1, 0, "reply", "asdf");
		assertTrue(replyDao.insert(replyDto) == 1);
		assertTrue(replyDao.count(1) == 2);
	}
	
	@Test
	public void selectAll() throws Exception {
		replyDao.deleteAll(1);
		ReplyDto replyDto = new ReplyDto(1, 0, "reply", "asdf");
		assertTrue(replyDao.insert(replyDto) == 1);
		assertTrue(replyDao.count(1) == 1);
		
		List<ReplyDto> list = replyDao.selectAll(1);
		assertTrue(list.size() == 1);
		
		replyDto = new ReplyDto(1, 0, "reply", "asdf");
		assertTrue(replyDao.insert(replyDto) == 1);
		assertTrue(replyDao.count(1) == 2);
		
		list = replyDao.selectAll(1);
		assertTrue(list.size() == 2);
	}
	
	@Test
	public void select() throws Exception {
		replyDao.deleteAll(1);
		ReplyDto replyDto = new ReplyDto(1, 0, "reply", "asdf");
		assertTrue(replyDao.insert(replyDto) == 1);
		assertTrue(replyDao.count(1) == 1);
		
		List<ReplyDto> list = replyDao.selectAll(1);
		String reply = list.get(0).getReply();
		String replier = list.get(0).getReplier();
		assertTrue(reply.equals(replyDto.getReply()));
		assertTrue(replier.equals(replyDto.getReplier()));
	}
	
	@Test
	public void update() throws Exception {
		replyDao.deleteAll(1);
		ReplyDto replyDto = new ReplyDto(1, 0, "reply", "asdf");
		assertTrue(replyDao.insert(replyDto) == 1);
		assertTrue(replyDao.count(1) == 1);
		
		List<ReplyDto> list = replyDao.selectAll(1);
		replyDto.setRno(list.get(0).getRno());
		replyDto.setReply("reply2");
		assertTrue(replyDao.update(replyDto) == 1);
		
		list = replyDao.selectAll(1);
		String reply = list.get(0).getReply();
		String replier = list.get(0).getReplier();
		assertTrue(reply.equals(replyDto.getReply()));
		assertTrue(replier.equals(replyDto.getReplier()));
	}
}
