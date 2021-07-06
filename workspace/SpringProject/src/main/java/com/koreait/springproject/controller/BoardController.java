package com.koreait.springproject.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.koreait.springproject.boardcommand.DeleteBoardCommand;
import com.koreait.springproject.boardcommand.InsertBoardCommand;
import com.koreait.springproject.boardcommand.SearchCommand;
import com.koreait.springproject.boardcommand.SelectBoardByNoCommand;
import com.koreait.springproject.boardcommand.SelectBoardListCommand;
import com.koreait.springproject.boardcommand.UpdateBoardCommand;

@Controller
public class BoardController {
	private SqlSession sqlSession;
	private SelectBoardListCommand selectBoardListCommand;
	private InsertBoardCommand insertBoardCommand;
	private SelectBoardByNoCommand selectBoardByNoCommand;
	private UpdateBoardCommand updateBoardCommand;
	private DeleteBoardCommand deleteBoardCommand;
	private SearchCommand searchCommand;
	
	
	
	public BoardController(SqlSession sqlSession, 
						   SelectBoardListCommand selectBoardListCommand,
						   InsertBoardCommand insertBoardCommand,
						   SelectBoardByNoCommand selectBoardByNoCommand,
						   UpdateBoardCommand updateBoardCommand,
						   DeleteBoardCommand deleteBoardCommand,
						   SearchCommand searchCommand) {
		super();
		this.sqlSession = sqlSession;
		this.selectBoardListCommand = selectBoardListCommand;
		this.insertBoardCommand = insertBoardCommand;
		this.selectBoardByNoCommand = selectBoardByNoCommand;
		this.updateBoardCommand = updateBoardCommand;
		this.deleteBoardCommand = deleteBoardCommand;
		this.searchCommand = searchCommand;
	}


	// 게시글 목록가지고 게시글 페이지로 가기
	@GetMapping(value="selectBoardList.do")
	public String selectBoardList(HttpServletRequest request,
								  Model model) {
		model.addAttribute("request", request);
		selectBoardListCommand.execute(sqlSession, model);
		return "board/board";
	}
	
	// 게시물 작성
	@PostMapping(value="insertBoard.do")
	public String insertBoard(MultipartHttpServletRequest multipartRequest,
							  Model model) {
		model.addAttribute("multipartRequest", multipartRequest);
		insertBoardCommand.execute(sqlSession, model);
		return "redirect:selectBoardList.do";
	}
	
	// 게시물 내용보기
	@GetMapping(value="selectBoardByNo.do")
	public String selectBoardByNo(HttpServletRequest request,
								  Model model) {
		model.addAttribute("request", request);
		selectBoardByNoCommand.execute(sqlSession, model);
		return "board/viewBoard";
	}
	
	// 게시물 수정
	@PostMapping(value="updateBoard.do")
	public String updateBoard(MultipartHttpServletRequest multipartRequest,
							  Model model) {
		model.addAttribute("multipartRequest", multipartRequest);
		updateBoardCommand.execute(sqlSession, model);
		return "redirect:selectBoardByNo.do?bno=" + multipartRequest.getParameter("bno");
	}
	
	// 게시물 삭제
	@PostMapping(value="deleteBoard.do")
	public String deleteBoard(MultipartHttpServletRequest multipartRequest,
							  Model model) {
		model.addAttribute("multipartRequest", multipartRequest);
		deleteBoardCommand.execute(sqlSession, model);
		return "redirect:selectBoardList.do";
	}

	// 검색
	@GetMapping(value="search.do")
	public String search(HttpServletRequest request,
						 Model model) {
		model.addAttribute("request", request);
		searchCommand.execute(sqlSession, model);
		return "board/searchList";
	}
	
	
	
	
}
