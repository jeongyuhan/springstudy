package com.koreait.springproject.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.springproject.membercommand.DeleteMemberCommand;
import com.koreait.springproject.membercommand.EmailAuthCommand;
import com.koreait.springproject.membercommand.FindIdCommand;
import com.koreait.springproject.membercommand.FindPwCommand;
import com.koreait.springproject.membercommand.IdCheckCommand;
import com.koreait.springproject.membercommand.JoinCommand;
import com.koreait.springproject.membercommand.LoginCommand;
import com.koreait.springproject.membercommand.LogoutCommand;

@Controller
public class MemberController {
	// field
	private SqlSession sqlSession;
	private LoginCommand loginCommand;
	private LogoutCommand logoutCommand;
	private IdCheckCommand idCheckCommand;
	private EmailAuthCommand emailAuthCommand;
	private JoinCommand joinCommand;
	private DeleteMemberCommand deleteMemberCommand;
	private FindIdCommand findIdCommand;
	private FindPwCommand findPwCommand;
	
	// constructor
	public MemberController(SqlSession sqlSession,
							LoginCommand loginCommand,
							LogoutCommand logoutCommand,
							IdCheckCommand idCheckCommand,
							EmailAuthCommand emailAuthCommand,
							JoinCommand joinCommand,
							DeleteMemberCommand deleteMemberCommand,
							FindIdCommand findIdCommand,
							FindPwCommand findPwCommand) {
		super();
		this.sqlSession = sqlSession;
		this.loginCommand = loginCommand;
		this.logoutCommand = logoutCommand;
		this.idCheckCommand = idCheckCommand;
		this.emailAuthCommand = emailAuthCommand;
		this.joinCommand = joinCommand;
		this.deleteMemberCommand = deleteMemberCommand;
		this.findIdCommand = findIdCommand;
		this.findPwCommand = findPwCommand;
	}

	// 로그인
	@PostMapping(value="login.do")
	public String login(HttpServletRequest request,
						Model model) {
		model.addAttribute("request", request);
		loginCommand.execute(sqlSession, model);
		return "redirect:/";
	}
	
	// 로그아웃
	@GetMapping(value="logout.do")
	public String logout(HttpSession session,
					     Model model) {
		model.addAttribute("session", session);
		logoutCommand.execute(sqlSession, model);
		return "redirect:/";
	}
	
	// 아이디 검사
	@GetMapping(value="idCheck.do", produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> idCheck(HttpServletRequest request,
									   Model model) {
		model.addAttribute("request", request);
		return idCheckCommand.execute(sqlSession, model);
	}
	
	// 이메일 인증
	@GetMapping(value="verifyNum.do", produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, String> verifyNum(HttpServletRequest request,
										 Model model) {
		model.addAttribute("request", request);
		return emailAuthCommand.execute(sqlSession, model);
	}
	
	// 회원 가입
	@PostMapping(value="join.do")
	public String join(HttpServletRequest request,
					   Model model) {
		model.addAttribute("request", request);
		joinCommand.execute(sqlSession, model);
		return "redirect:/";		
	}
	
	// 회원 탈퇴
	@GetMapping(value="deleteMember.do")
	public String deleteMember(HttpSession session,
							   Model model) {
		model.addAttribute("session", session);
		deleteMemberCommand.execute(sqlSession, model);
		return "redirect:/";
	}
	
	// 아이디 찾기
	@PostMapping(value="findId.do")
	public String findId(HttpServletRequest request,
						 Model model) {
		model.addAttribute("request", request);
		findIdCommand.execute(sqlSession, model);
		return "member/findIdResult";
	}
	
	// 비밀번호 찾기
	@PostMapping(value="findPw.do")
	public String findPw(HttpServletRequest request,
						 Model model) {
		model.addAttribute("request", request);
		findPwCommand.execute(sqlSession, model);
		return "member/findPwResult";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
