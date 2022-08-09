package com.bootcamp.induk.service;

import java.util.List;

import com.bootcamp.induk.mapper.BoardMapper;
import com.bootcamp.induk.mapper.ReplyMapper;
import com.bootcamp.induk.service.interfaces.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.induk.domain.ReplyDto;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {
	
	private final BoardMapper boardMapper;
	private final ReplyMapper replyMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int writeReply(ReplyDto replyDto) throws Exception {
		boardMapper.updateCommentCnt(replyDto.getBno(), 1);
		return replyMapper.insertReply(replyDto);
	}

	@Override
	public List<ReplyDto> readReplyList(Integer bno) throws Exception {
		return replyMapper.selectReplyList(bno);
	}

	@Override
	public ReplyDto readReply(Integer rno) throws Exception {
		return replyMapper.selectReply(rno);
	}

	@Override
	public int modifyReply(ReplyDto replyDto) throws Exception {
		return replyMapper.updateReply(replyDto);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int removeReply(Integer rno, Integer bno, String replier) throws Exception {
		boardMapper.updateCommentCnt(bno, -1);
		int rowCnt = replyMapper.deleteReply(rno, replier);
		return rowCnt;
	}

	@Override
	public int getCount(Integer bno) throws Exception {
		return replyMapper.getCount(bno);
	}
}
