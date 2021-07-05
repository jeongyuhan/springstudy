package com.koreait.springproject.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.koreait.springproject.boardcommand.DeleteBoardCommand;
import com.koreait.springproject.boardcommand.InsertBoardCommand;
import com.koreait.springproject.boardcommand.SelectBoardByNoCommand;
import com.koreait.springproject.boardcommand.SelectBoardListCommand;
import com.koreait.springproject.boardcommand.UpdateBoardCommand;
import com.koreait.springproject.membercommand.DeleteMemberCommand;
import com.koreait.springproject.membercommand.EmailAuthCommand;
import com.koreait.springproject.membercommand.FindIdCommand;
import com.koreait.springproject.membercommand.FindPwCommand;
import com.koreait.springproject.membercommand.IdCheckCommand;
import com.koreait.springproject.membercommand.JoinCommand;
import com.koreait.springproject.membercommand.LoginCommand;
import com.koreait.springproject.membercommand.LogoutCommand;

@Configuration
public class BeanConfiguration {
	
	@Bean
	 public DriverManagerDataSource dataSource()	{
   	DriverManagerDataSource dataSource = new DriverManagerDataSource();
   	dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
   	dataSource.setUrl("jdbc:oracle:thin:@127.0.0.1:1521:xe");
   	dataSource.setUsername("spring");
   	dataSource.setPassword("1111");
   	return dataSource;
	}
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource());
		sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/koreait/springproject/dao/*.xml"));
		return sqlSessionFactory.getObject();
	}
	@Bean
	public SqlSessionTemplate sqlSession() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}
	
	//---------Member---------//
	
	// 로그인 
	@Bean
	public LoginCommand loginCommand() {
		return new LoginCommand();
	}
	
	// 로그아웃
	@Bean
	public LogoutCommand logoutCommand() {
		return new LogoutCommand();
	}
	
	// 아이디 중복 검사
	@Bean
	public IdCheckCommand idCheckCommand() {
		return new IdCheckCommand();
	}
	
	// 이메일 인증
	@Bean
	public EmailAuthCommand emailAuthCommand() {
		return new EmailAuthCommand();
	}
	
	// 회원가입
	@Bean
	public JoinCommand joinCommand() {
		return new JoinCommand();
	}
	
	// 회원탈퇴
	@Bean
	public DeleteMemberCommand deleteMemberCommand() {
		return new DeleteMemberCommand();
	}
	
	// 아이디 찾기
	@Bean
	public FindIdCommand findIdCommand() {
		return new FindIdCommand();
	}
	
	// 비밀번호 찾기
	@Bean
	public FindPwCommand findPwCommand() {
		return new FindPwCommand();
	}
	
	
	//---------Board---------//
	
	// 전체 게시물
	@Bean
	public SelectBoardListCommand selectBoardListCommand() {
		return new SelectBoardListCommand();
	}
	
	// 게시물 파일 처리
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("UTF-8");
		multipartResolver.setMaxUploadSize(1024 * 1024 * 10); // 바이트 단위 (10MB)
		return multipartResolver;
	}
	
	// 게시물 작성
	@Bean
	public InsertBoardCommand insertBoardCommand() {
		return new InsertBoardCommand();
	}
	
	// 게시물 내용보기
	@Bean
	public SelectBoardByNoCommand selectBoardByNoCommand() {
		return new SelectBoardByNoCommand();
	}
	
	// 게시물 수정하기
	@Bean
	public UpdateBoardCommand updateBoardCommand() {
		return new UpdateBoardCommand();
	}
	
	// 게시물 삭제하기
	@Bean
	public DeleteBoardCommand deleteBoardCommand() {
		return new DeleteBoardCommand();
	}
	
	
	
	
	
	
	
	
	
	
	
}
