package com.bootcamp.induk.service.interfaces;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.induk.domain.ReplyDto;

public interface ReplyService {

	@Transactional(rollbackFor = Exception.class)
	int writeReply(ReplyDto replyDto) throws Exception;

	List<ReplyDto> readReplyList(Integer bno) throws Exception;
	ReplyDto readReply(Integer rno) throws Exception;

	int modifyReply(ReplyDto replyDto) throws Exception;

	@Transactional(rollbackFor = Exception.class)
	int removeReply(Integer rno, Integer bno, String replier) throws Exception;

	int getCount(Integer bno) throws Exception;
}