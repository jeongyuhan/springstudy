package com.koreait.contact04.command;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

public interface ContactCommand {
	
	public void execute(SqlSession sqlSession, Model model);
	
}
