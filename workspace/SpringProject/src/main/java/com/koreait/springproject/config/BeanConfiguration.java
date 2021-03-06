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
import com.koreait.springproject.boardcommand.SearchQueryCommand;
import com.koreait.springproject.boardcommand.SelectBoardByNoCommand;
import com.koreait.springproject.boardcommand.SelectBoardListCommand;
import com.koreait.springproject.boardcommand.UpdateBoardCommand;
import com.koreait.springproject.boardreplycommand.BoardReplyInsertCommand;
import com.koreait.springproject.boardreplycommand.SelectAllReplyCommand;
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
	
	// ????????? 
	@Bean
	public LoginCommand loginCommand() {
		return new LoginCommand();
	}
	
	// ????????????
	@Bean
	public LogoutCommand logoutCommand() {
		return new LogoutCommand();
	}
	
	// ????????? ?????? ??????
	@Bean
	public IdCheckCommand idCheckCommand() {
		return new IdCheckCommand();
	}
	
	// ????????? ??????
	@Bean
	public EmailAuthCommand emailAuthCommand() {
		return new EmailAuthCommand();
	}
	
	// ????????????
	@Bean
	public JoinCommand joinCommand() {
		return new JoinCommand();
	}
	
	// ????????????
	@Bean
	public DeleteMemberCommand deleteMemberCommand() {
		return new DeleteMemberCommand();
	}
	
	// ????????? ??????
	@Bean
	public FindIdCommand findIdCommand() {
		return new FindIdCommand();
	}
	
	// ???????????? ??????
	@Bean
	public FindPwCommand findPwCommand() {
		return new FindPwCommand();
	}
	
	
	//---------Board---------//
	
	// ?????? ?????????
	@Bean
	public SelectBoardListCommand selectBoardListCommand() {
		return new SelectBoardListCommand();
	}
	
	// ????????? ?????? ??????
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("UTF-8");
		multipartResolver.setMaxUploadSize(1024 * 1024 * 10); // ????????? ?????? (10MB)
		return multipartResolver;
	}
	
	// ????????? ??????
	@Bean
	public InsertBoardCommand insertBoardCommand() {
		return new InsertBoardCommand();
	}
	
	// ????????? ????????????
	@Bean
	public SelectBoardByNoCommand selectBoardByNoCommand() {
		return new SelectBoardByNoCommand();
	}
	
	// ????????? ????????????
	@Bean
	public UpdateBoardCommand updateBoardCommand() {
		return new UpdateBoardCommand();
	}
	
	// ????????? ????????????
	@Bean
	public DeleteBoardCommand deleteBoardCommand() {
		return new DeleteBoardCommand();
	}
	
	// ??????
	@Bean
	public SearchQueryCommand searchQueryCommand() {
		return new SearchQueryCommand();
	}
	
	
	//---------BoardReply---------//
	
	// ?????? ??????
	@Bean
	public BoardReplyInsertCommand boardReplyInsertCommand() {
		return new BoardReplyInsertCommand();
	}
	
	// ?????? ?????? ????????????
	@Bean
	public SelectAllReplyCommand selectAllReplyCommand() {
		return new SelectAllReplyCommand();
	}
	
	
	
}
