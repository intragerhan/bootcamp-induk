package com.bootcamp.induk.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.bootcamp.induk.service.interfaces.BoardService;
import com.bootcamp.induk.service.interfaces.ReplyService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bootcamp.induk.domain.BoardDto;
import com.bootcamp.induk.domain.ReplyDto;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class ReplyServiceImplTest {
    
	@Autowired
	private ReplyService replyService;
	@Autowired
	private BoardService boardService;

	@DisplayName("댓글 등록")
	@Test
	public void insert() throws Exception {
		// given
		ReplyDto replyDto = new ReplyDto(355, 0, "hello", "asdf");
		// when
		int result = replyService.writeReply(replyDto);
		// then
		assertThat(result).isEqualTo(1);
	}

	@DisplayName("댓글 상세 조회")
	@Test
	public void select() throws Exception {
		// given
		Integer rno = 5;
		// when
		ReplyDto replyDto = replyService.readReply(rno);
		// then
		assertThat(replyDto.getRno()).isEqualTo(5);
	}

	@Test
	public void remove() throws Exception {
		boardService.removeBoardList();

		BoardDto boardDto = new BoardDto("hello", "hello", "asdf");
		assertTrue(boardService.writeBoard(boardDto) == 1);
		Integer bno = boardService.getBoardList().get(0).getBno();
		String writer = boardService.getBoardList().get(0).getWriter();
        System.out.println("bno = " + bno);
        System.out.println("writer = " + writer);

		boardService.removeBoard(bno, writer);
		ReplyDto replyDto = new ReplyDto(bno, 0, "hi", "qwer");

		assertTrue(boardService.readBoard(bno).getReply_cnt() == 0);
		assertTrue(replyService.writeReply(replyDto) == 1);
		assertTrue(boardService.readBoard(bno).getReply_cnt() == 1);

		Integer rno = replyService.readReplyList(bno).get(0).getRno();

		// 일부러 예외를 발생시키고 트랜잭션이 취소되는지 확인해야 함
		assertTrue(replyService.removeReply(rno, bno, replyDto.getReplier()) == 1);
		assertTrue(boardService.readBoard(bno).getReply_cnt() == 0);
	}

	@Test
	public void write() throws Exception {
		boardService.removeBoardList();

		BoardDto boardDto = new BoardDto("hello", "hello", "asdf");
		assertTrue(boardService.writeBoard(boardDto) == 1);
        Integer bno = boardService.getBoardList().get(0).getBno();
        Integer rno = replyService.readReplyList(bno).get(0).getRno();
        String replier = replyService.readReply(rno).getReplier();
		System.out.println("bno = " + bno);
        System.out.println("rno = " + rno);
        System.out.println("replier = " + replier);

		replyService.removeReply(rno, bno, replier);
		ReplyDto replyDto = new ReplyDto(bno, 0, "hi", "qwer");

		assertTrue(boardService.readBoard(bno).getReply_cnt() == 0);
		assertTrue(replyService.writeReply(replyDto) == 1);

		assertTrue(boardService.readBoard(bno).getReply_cnt() == 1);
	}
}
