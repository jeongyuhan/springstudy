package com.koreait.springproject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.springproject.boardreplycommand.InsertBoardReplyCommand;
import com.koreait.springproject.dao.BoardReplyDAO;
import com.koreait.springproject.dto.BoardReplyDTO;

@Controller
public class BoardReplyController {

	private SqlSession sqlSession;
	private InsertBoardReplyCommand insertBoardReplyCommand;

	public BoardReplyController(SqlSession sqlSession,
								InsertBoardReplyCommand insertBoardReplyCommand) {
		super();
		this.sqlSession = sqlSession;
		this.insertBoardReplyCommand = insertBoardReplyCommand;
	}
	
	@GetMapping(value="selectAllReply.do", produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> selectAllReply(HttpServletRequest request,
											  Model model) {
		model.addAttribute("request", request);
		return "";
	}	
	
	
	@GetMapping(value="insertReply.do", produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> insertReply(HttpServletRequest request,
										   Model model) {
		model.addAttribute("request", request);
		insertBoardReplyCommand.execute(sqlSession, model);		
		return insertBoardReplyCommand.execute(sqlSession, model);
	}
	
	
}
