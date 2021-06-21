package com.koreait.board03.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.koreait.board03.command.DeleteBoardCommand;
import com.koreait.board03.command.InsertBoardCommand;
import com.koreait.board03.command.SelectBoardListCommand;
import com.koreait.board03.command.SelectBoardViewCommand;
import com.koreait.board03.command.UpdateBoardCommand;
import com.koreait.board03.dto.Board;

@Controller
public class BoardController {
	
	// field 
	private SqlSession sqlSession; // private SqlSessionTemplate sqlSession;(SqlSessionTemplate 클래스는 SqlSession 인터페이스를 구현한 클래스이기 때문이다.)
	private SelectBoardListCommand selectBoardListCommand;
	private SelectBoardViewCommand selectBoardViewCommand;
	private InsertBoardCommand insertBoardCommand;
	private UpdateBoardCommand updateBoardCommand;
	private DeleteBoardCommand deleteBoardCommand;
	
	@Autowired
	public BoardController(SqlSession sqlSession, 
						   SelectBoardListCommand selectBoardListCommand,
						   SelectBoardViewCommand selectBoardViewCommand, 
						   InsertBoardCommand insertBoardCommand,
						   UpdateBoardCommand updateBoardCommand, 
						   DeleteBoardCommand deleteBoardCommand) {
		super();
		this.sqlSession = sqlSession;
		this.selectBoardListCommand = selectBoardListCommand;
		this.selectBoardViewCommand = selectBoardViewCommand;
		this.insertBoardCommand = insertBoardCommand;
		this.updateBoardCommand = updateBoardCommand;
		this.deleteBoardCommand = deleteBoardCommand;
	}

	
	@GetMapping(value="/")
	public String index() {
		return "index";
	}
	
	@GetMapping(value="selectBoardList.do")
	public String selectBoardList(Model model) {
		selectBoardListCommand.execute(sqlSession, model);
		return "board/list";
	}
	
	@GetMapping(value="selectBoardByNo.do")
	public String selectBoardByNo(HttpServletRequest request,
								  Model model) {
		model.addAttribute("request", request);
		selectBoardViewCommand.execute(sqlSession, model);
		return "board/view";
	}
	
	@GetMapping(value="insertBoardPage.do")
	public String insertBoardPage() {
		return "board/insert";
	}
	
	@GetMapping(value="insertBoard.do")
	public String insertBoard(HttpServletRequest request,
							  Model model) {
		model.addAttribute("request", request);
		insertBoardCommand.execute(sqlSession, model);
		return "redirect:selectBoardList.do";
	}
	
	@PostMapping(value="updateBoardPage.do")
	public String updateBoardPage(Board board,
								  Model model) {
		model.addAttribute("board", board);
		return "board/update";
	}
	
	@PostMapping(value="updateBoard.do")
	public String updateBoard(HttpServletRequest request,
							  Model model) {
		model.addAttribute("request", request);
		updateBoardCommand.execute(sqlSession, model);
		return "redirect:selectBoardByNo.do?no=" + request.getParameter("no");
	}
	
	@GetMapping(value="deleteBoard.do")
	public String deleteBoard(HttpServletRequest request,
							  Model model) {
		model.addAttribute("request", request);
		deleteBoardCommand.execute(sqlSession, model);
		return "redirect:selectBoardList.do";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
