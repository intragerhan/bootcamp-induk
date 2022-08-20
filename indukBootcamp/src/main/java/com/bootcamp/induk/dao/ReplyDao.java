package com.bootcamp.induk.dao;

import java.util.List;

import com.bootcamp.induk.domain.ReplyDto;

public interface ReplyDao {
	int count(Integer bno) throws Exception;
	int deleteAll(Integer bno);
	int delete(Integer rno, String replier) throws Exception;
	int insert(ReplyDto dto) throws Exception;
	List<ReplyDto> selectAll(Integer bno) throws Exception;
	ReplyDto select(Integer rno) throws Exception;
	int update(ReplyDto dto) throws Exception;
}
