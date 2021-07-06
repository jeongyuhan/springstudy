package com.koreait.springproject.boardreplycommand;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

public interface BoardReplyCommand {
	
	public void execute(SqlSession sqlSession, Model model);
	
}
