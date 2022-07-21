package com.bootcamp.induk;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.junit.Assert.*;

public class DBConfigTest {
	@Test
	public void springJdbcConnectionTest() throws Exception {
		ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
		DataSource ds = ac.getBean(DataSource.class);
		
		Connection conn = ds.getConnection();	// 데이터베이스의 연결을 얻는다.
		
		System.out.println("conn = " + conn);
		assertTrue(conn != null);	// 괄호 안의 조건식이 true면 테스트 성공, 아니면 실패
	}
}
