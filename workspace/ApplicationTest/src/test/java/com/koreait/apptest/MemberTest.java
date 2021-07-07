package com.koreait.apptest;

import static org.junit.Assert.assertEquals;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.koreait.apptest.config.BeanConfiguration;
import com.koreait.apptest.dao.MemberDAO;
import com.koreait.apptest.dto.Member;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {BeanConfiguration.class})
public class MemberTest {

	// JUnit 테스트는 스프링을 모두 돌리지 않는다.
	// 대표적으로 @Autowired 같은 코드는 동작하지 않는다.
	// Oracle JDBC도 tomcat에 넣어 두면 동작하지 않는다.(현재 프로젝트에 포함되어 있어야한다.(pom.xml 참고))
	
	// JUnit 테스트 시 스프링 모든 기능 활용을 위해서
	// spring-test 디펜던시를 추가해야한다. (스프링 프레임워크와 버전을 맞춰주면된다.)
	
	// spring-test 디펜던시가 지원하는 애너테이션
	// @RunWith : 스프링을 함께 돌리는 테스트
	// @ContextConfiguration : Bean을 찾는 역할
	
	// field
	@Autowired
	private SqlSession sqlSession;
	
	@Test // 
	public void joinTest() {
		Member member = new Member();
		member.setId("test2");
		member.setPw("1111");
		member.setName("테스트");
		member.setEmail("test@web.com");
		
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		
		int count = memberDAO.join(member);
		
		assertEquals("가입 실패", 1, count); // 1과 count가 같으면 검사 통과
											 // 1과 count가 다르면 검사 실패(가입 실패 message가 출력된다.)
		
	}
	
	// 아이디 중복 점검 테스트
	// admin 아이디를 가진 아이디가 있으면 검사 통과, 없으면 실패
	@Test
	public void idCheckTest() {
		
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		
		int count = memberDAO.idCheck("admin");
		
		assertEquals("중복체크실패", 1, count);
	}

}
