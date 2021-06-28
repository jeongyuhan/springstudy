package com.koreait.ajax.command;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

public interface MemberCommand {
	
	public Map<String, Object> execute(SqlSession sqlSession, Model model); 
	// ajax는 값을 반환하기 때문에 인터페이스에서 값을 반환할 수 있도록 Map을 사용해준다.
	// ajax는 값을 json으로 받아오기 때문에 Map의 Object로 처리해준다.
}
