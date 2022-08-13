package com.bootcamp.induk.mapper;

import com.bootcamp.induk.domain.ReplyDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface ReplyMapper {
	int insertReply(ReplyDto dto) throws Exception;
	List<ReplyDto> selectReplyList(Integer bno) throws Exception;
	ReplyDto selectReply(Integer rno) throws Exception;
	int updateReply(ReplyDto dto) throws Exception;
	int deleteReply(Integer rno, String replier) throws Exception;
    int getCount(Integer bno) throws Exception;
}
