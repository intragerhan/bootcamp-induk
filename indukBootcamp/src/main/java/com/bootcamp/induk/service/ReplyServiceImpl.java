package com.bootcamp.induk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.induk.dao.BoardDao;
import com.bootcamp.induk.dao.ReplyDao;
import com.bootcamp.induk.domain.ReplyDto;

@Service
public class ReplyServiceImpl implements ReplyService {
	@Autowired
	BoardDao boardDao;
	@Autowired
	ReplyDao commentDao;

//	public CommentServiceImpl(CommentDao commentDao, BoardDao boardDao) {
//		this.commentDao = commentDao;
//		this.boardDao = boardDao;
//	}
	
	@Override
	public int getCount(Integer bno) throws Exception {
		return commentDao.count(bno);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int remove(Integer cno, Integer bno, String commenter) throws Exception {
		int rowCnt = boardDao.updateCommentCnt(bno, -1);
		System.out.println("updateCommentCnt - rowCnt = " + rowCnt);
//		throw new Exception("test");
		rowCnt = commentDao.delete(cno, commenter);
		System.out.println("rowCnt = " + rowCnt);
		return rowCnt;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int write(ReplyDto commentDto) throws Exception {
		boardDao.updateCommentCnt(commentDto.getBno(), 1);
//			throw new Exception("test");
		return commentDao.insert(commentDto);
	}
	
	@Override
	public List<ReplyDto> getList(Integer bno) throws Exception {
//		throw new Exception("test");
		return commentDao.selectAll(bno);
	}
	
	@Override
	public ReplyDto read(Integer cno) throws Exception {
		return commentDao.select(cno);
	}
	
	@Override
	public int modify(ReplyDto commentDto) throws Exception {
		return commentDao.update(commentDto);
	}
}
