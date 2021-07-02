package com.koreait.springproject.membercommand;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.springproject.dao.MemberDAO;
import com.koreait.springproject.dto.MemberDTO;

public class DeleteMemberCommand implements MemberCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpSession session = (HttpSession)map.get("session");
		
		long no = ((MemberDTO)session.getAttribute("loginUser")).getNo();
		
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		
		int count = memberDAO.deleteMember(no);
		
		if(count > 0) {
			session.invalidate();
		}
		
	}

}
