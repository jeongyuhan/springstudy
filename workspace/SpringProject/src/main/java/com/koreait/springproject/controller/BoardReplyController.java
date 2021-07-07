package com.koreait.springproject.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.springproject.boardreplycommand.BoardReplyInsertCommand;
import com.koreait.springproject.boardreplycommand.SelectAllReplyCommand;
import com.koreait.springproject.dto.QueryDTO;

@Controller
public class BoardReplyController {

	private SqlSession sqlSession;
	private BoardReplyInsertCommand boardReplyInsertCommand;
	private SelectAllReplyCommand selectAllReplyCommand;

	public BoardReplyController(SqlSession sqlSession,
								BoardReplyInsertCommand boardReplyInsertCommand,
								SelectAllReplyCommand selectAllReplyCommand) {
		super();
		this.sqlSession = sqlSession;
		this.boardReplyInsertCommand = boardReplyInsertCommand;
		this.selectAllReplyCommand = selectAllReplyCommand;
	}
	
	@PostMapping(value="selectAllReply.do",
				 produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> selectAllReply(@RequestBody QueryDTO queryDTO,
											  Model model){
		model.addAttribute("queryDTO", queryDTO);
		return selectAllReplyCommand.execute(sqlSession, model);
	}
	
	@GetMapping(value="insertReply.do",
			    produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> insertReply(HttpServletRequest request,
										   Model model) {
		model.addAttribute("request", request);
		return boardReplyInsertCommand.execute(sqlSession, model);
	}
	
	
}
