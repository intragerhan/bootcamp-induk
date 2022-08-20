package com.bootcamp.induk.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bootcamp.induk.domain.ReplyDto;

@Repository
public class ReplyDaoImpl implements ReplyDao {
	@Autowired
	private SqlSession session;
	
	private static String namespace="com.bootcamp.induk.dao.ReplyMapper.";
	
	@Override
	public int count(Integer bno) throws Exception {
		return session.selectOne(namespace + "count", bno);
	}	// T selectOne(String statement)
	
	@Override
	public int deleteAll(Integer bno) {
		return session.delete(namespace + "deleteAll", bno);
	}	// int delete(String statement)
	
	@Override
	public int delete(Integer rno, String replier) throws Exception {
		Map map = new HashMap();
		map.put("rno", rno);
		map.put("replier", replier);
		return session.delete(namespace + "delete", map);
	}	// int delete(String statement, Object parameter)
	
	@Override
	public int insert(ReplyDto dto) throws Exception {
		return session.insert(namespace + "insert", dto);
	}	// int insert(String statement, Object parameter)
	
	@Override
	public List<ReplyDto> selectAll(Integer bno) throws Exception {
		return session.selectList(namespace + "selectAll", bno);
	}	// List<E> selectList(String statement)
	
	@Override
	public ReplyDto select(Integer rno) throws Exception {
		return session.selectOne(namespace + "select", rno);
	}	// T selectOne(String statement, Object parameter)
	
	@Override
	public int update(ReplyDto dto) throws Exception {
		return session.update(namespace + "update", dto);
	}	// int update(String statement, Object parameter)
}
