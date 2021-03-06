package com.koreait.ajax.command;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.ajax.dao.MemberDAO;
import com.koreait.ajax.dto.Member;

public class SelectMemberViewCommand implements MemberCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		long no = (Long)map.get("no");
		
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		Member member = memberDAO.selectMemberByNo(no);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("member", member);
		resultMap.put("exists", member != null);
		
		return resultMap;
	}

}
