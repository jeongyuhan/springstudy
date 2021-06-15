package com.koreait.mvc03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koreait.mvc03.dto.Member;

/*
	똑같은 매핑을 2개이상 사용하면 서버가 돌아가지 않는다.
*/

@Controller
public class LoginController {

	// 단순 페이지 이동
	@RequestMapping("loginPage.do")
	public String a() {
		return "member/login";
	}
	
	
	// form에서 받아온 데이터 처리
	/*
	@RequestMapping("login.do")
	public String b(HttpServletRequest request, Model model) {
		model.addAttribute("id", request.getParameter("id"));
		model.addAttribute("pw", request.getParameter("pw"));
		return "member/loginResult";
	}
	*/
	
	/*
	@RequestMapping("login.do")
	public String c(@RequestParam("id") String id,
					@RequestParam("pw") String pw,
					Model model) {
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		
		return "member/loginResult";
	}
	*/
	
	@RequestMapping("login.do")
	public String d(Member member, Model model) {
		model.addAttribute("id", member.getId());
		model.addAttribute("pw", member.getPw());
		
		return "member/loginResult";
	}
	
	
	
}
