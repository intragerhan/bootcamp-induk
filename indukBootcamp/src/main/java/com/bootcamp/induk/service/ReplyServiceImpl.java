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
	ReplyDao replyDao;

//	public replyServiceImpl(replyDao replyDao, BoardDao boardDao) {
//		this.replyDao = replyDao;
//		this.boardDao = boardDao;
//	}
	
	@Override
	public int getCount(Integer bno) throws Exception {
		return replyDao.count(bno);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int remove(Integer rno, Integer bno, String replier) throws Exception {
		int rowCnt = boardDao.updateReplyCnt(bno, -1);
		System.out.println("updatereplyCnt - rowCnt = " + rowCnt);
//		throw new Exception("test");
		rowCnt = replyDao.delete(rno, replier);
		System.out.println("rowCnt = " + rowCnt);
		return rowCnt;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int write(ReplyDto replyDto) throws Exception {
		boardDao.updateReplyCnt(replyDto.getBno(), 1);
//			throw new Exception("test");
		return replyDao.insert(replyDto);
	}
	
	@Override
	public List<ReplyDto> getList(Integer bno) throws Exception {
//		throw new Exception("test");
		return replyDao.selectAll(bno);
	}
	
	@Override
	public ReplyDto read(Integer rno) throws Exception {
		return replyDao.select(rno);
	}
	
	@Override
	public int modify(ReplyDto replyDto) throws Exception {
		return replyDao.update(replyDto);
	}
}
