package org.springframe.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

// test에 사용되는 클래스
@RunWith(SpringJUnit4ClassRunner.class)
// 설정파일 지정 -> 서버와 상관이 없음 : root-context.xml
@ContextConfiguration("file:src/main/webapp/WEB-INF/config/root-context.xml")
// log 객체 생성 -> lombok : log 이름으로 처리
@Log4j
public class DataSourceTests {
	
	// 자동 DI - root-context.xml에 bean태그로 설정
//	@Setter(onMethod_ = @Autowired)
	@Autowired
	private DataSource dataSource;
	
	// 자동 DI - root-context.xml에 bean태그로 설정
//	@Setter(onMethod_ = @Autowired)
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Test
	public void testConnection() {
		log.info("--------------[CPTEST]--------------------");
		// try(resource) -> try가 종료되면 resource 자동닫기가 된다.
		try(Connection conn = dataSource.getConnection()) {
			log.info(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMybatis() {
		log.info("--------------[Mybatis 테스트]--------------------");
		try( SqlSession session = sqlSessionFactory.openSession();
				Connection conn = session.getConnection()) {
			log.info(session);
			log.info(conn);
//			session.select(null, null);
//			session.selectOne(null);
//			session.selectList(null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
//			e.printStackTrace();
		}
	}
	
}
