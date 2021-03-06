package com.koreait.member.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.koreait.member.command.EmailAuthCommand;
import com.koreait.member.command.FindIdCommand;
import com.koreait.member.command.FindPwCommand;
import com.koreait.member.command.IdCheckCommand;
import com.koreait.member.command.JoinCommand;
import com.koreait.member.command.LeaveCommand;
import com.koreait.member.command.LoginCommand;
import com.koreait.member.command.LogoutCommand;
import com.koreait.member.command.PresentPwCheckCommand;
import com.koreait.member.command.UpdateMemberCommand;
import com.koreait.member.command.UpdatePwCommand;

@Configuration
public class BeanConfiguration {
	
	@Bean
	public DriverManagerDataSource dataSource() {
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
		sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/koreait/member/dao/*.xml"));
		return sqlSessionFactory.getObject();
	}
	@Bean
	public SqlSessionTemplate sqlSession() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}
	
	@Bean
	public IdCheckCommand idCheckCommand() {
		return new IdCheckCommand();
	}
	
	@Bean
	public EmailAuthCommand emailAuthCommand() {
		return new EmailAuthCommand();
	}
	
	@Bean
	public JoinCommand joinCommand() {
		return new JoinCommand();
	}
	
	@Bean
	public LoginCommand loginCommand() {
		return new LoginCommand();
	}
	
	@Bean
	public LogoutCommand logoutCommand() {
		return new LogoutCommand();
	}
	
	@Bean
	public LeaveCommand leaveCommand() {
		return new LeaveCommand();
	}
	
	@Bean
	public UpdateMemberCommand updateMemberCommand() {
		return new UpdateMemberCommand();
	}
	
	@Bean
	public PresentPwCheckCommand presentPwCheckCommand() {
		return new PresentPwCheckCommand();
	}
	
	@Bean
	public UpdatePwCommand updatePwCommand() {
		return new UpdatePwCommand();
	}
	
	@Bean
	public FindIdCommand findIdCommand() {
		return new FindIdCommand();
	}
	
	@Bean
	public FindPwCommand findPwCommand() {
		return new FindPwCommand();
	}
	
	
}
