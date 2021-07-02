package com.koreait.springproject.membercommand;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.springproject.dao.MemberDAO;
import com.koreait.springproject.dto.MemberDTO;

public class FindIdCommand implements MemberCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String email = request.getParameter("email");
		
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		
		MemberDTO findUser = memberDAO.findId(email);
		
		if(findUser != null) {
			model.addAttribute("findUser", findUser);
		}
		
	}

}
