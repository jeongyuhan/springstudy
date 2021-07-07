package com.koreait.springproject.boardreplycommand;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

public interface BoardReplyCommand {
	
	public Map<String, Object> execute(SqlSession sqlSession, Model model);
	
}
