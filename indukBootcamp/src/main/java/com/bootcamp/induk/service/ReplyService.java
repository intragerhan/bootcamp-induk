package com.bootcamp.induk.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.induk.domain.ReplyDto;

public interface ReplyService {
	int getCount(Integer bno) throws Exception;
	
	@Transactional(rollbackFor = Exception.class)
	int remove(Integer cno, Integer bno, String commenter) throws Exception;
	
	@Transactional(rollbackFor = Exception.class)
	int write(ReplyDto commentDto) throws Exception;

	List<ReplyDto> getList(Integer bno) throws Exception;
	
	ReplyDto read(Integer cno) throws Exception;
	
	int modify(ReplyDto commentDto) throws Exception;
}