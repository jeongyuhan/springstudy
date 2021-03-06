package com.koreait.ajax.command;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.ui.Model;

import com.koreait.ajax.dao.MemberDAO;
import com.koreait.ajax.dto.Member;

public class InsertMemberCommand implements MemberCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		
		HttpServletResponse response = null;
		Map<String, Object> resultMap = null;
		
		try {
			Map<String, Object> map = model.asMap();
			Member member = (Member)map.get("member");
			response = (HttpServletResponse)map.get("response");
			
			MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
			int result = memberDAO.insertMember(member);
		
			resultMap = new HashMap<>();
			resultMap.put("result", result);
			

		} catch(DuplicateKeyException e) { // 키 위반 (아이디 중복, 기본키 위반)
			try {
				response.setContentType("text/html; charset=utf-8"); // 타입이 기억나지 않을 경우 response.setCharacterEncoding("utf-8"); 인코딩만 해라
				response.setStatus(1001);
				response.getWriter().println("이미 사용중인 아이디입니다.");				
			} catch(IOException e2) {
				e2.printStackTrace();
			}
		}
		return resultMap;
	} 	
}
