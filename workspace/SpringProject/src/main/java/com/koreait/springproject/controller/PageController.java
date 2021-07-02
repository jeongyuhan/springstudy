package com.koreait.springproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
	
	//---------Member---------//
	
	@GetMapping(value= {"/", "index.do"})
	public String index() {
		return "index";
	}
	
	@GetMapping(value="joinPage.do")
	public String joinPage() {
		return "member/join";
	}
	
	@GetMapping(value="findIdPage.do")
	public String findIdPage() {
		return "member/findId";
	}

	@GetMapping(value="findPwPage.do")
	public String findPwPage() {
		return "member/findPw";
	}
	
	
	//---------Board---------//
	
	@GetMapping(value="insertBoardPage.do")
	public String insertBoardPage() {
		return "board/insertBoard";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
