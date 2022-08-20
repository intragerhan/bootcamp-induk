package com.bootcamp.induk.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.induk.domain.ReplyDto;

public interface ReplyService {
	int getCount(Integer bno) throws Exception;
	
	@Transactional(rollbackFor = Exception.class)
	int remove(Integer rno, Integer bno, String replier) throws Exception;
	
	@Transactional(rollbackFor = Exception.class)
	int write(ReplyDto replyDto) throws Exception;

	List<ReplyDto> getList(Integer bno) throws Exception;
	
	ReplyDto read(Integer rno) throws Exception;
	
	int modify(ReplyDto replyDto) throws Exception;
}