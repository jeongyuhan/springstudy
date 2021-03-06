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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.koreait.springproject.boardcommand.DeleteBoardCommand;
import com.koreait.springproject.boardcommand.InsertBoardCommand;
import com.koreait.springproject.boardcommand.SearchQueryCommand;
import com.koreait.springproject.boardcommand.SelectBoardByNoCommand;
import com.koreait.springproject.boardcommand.SelectBoardListCommand;
import com.koreait.springproject.boardcommand.UpdateBoardCommand;
import com.koreait.springproject.dto.QueryDTO;

@Controller
public class BoardController {
	private SqlSession sqlSession;
	private SelectBoardListCommand selectBoardListCommand;
	private InsertBoardCommand insertBoardCommand;
	private SelectBoardByNoCommand selectBoardByNoCommand;
	private UpdateBoardCommand updateBoardCommand;
	private DeleteBoardCommand deleteBoardCommand;
	private SearchQueryCommand searchQueryCommand;
	
	
	
	
	public BoardController(SqlSession sqlSession, 
						   SelectBoardListCommand selectBoardListCommand,
						   InsertBoardCommand insertBoardCommand,
						   SelectBoardByNoCommand selectBoardByNoCommand,
						   UpdateBoardCommand updateBoardCommand,
						   DeleteBoardCommand deleteBoardCommand,
						   SearchQueryCommand searchQueryCommand) {
		super();
		this.sqlSession = sqlSession;
		this.selectBoardListCommand = selectBoardListCommand;
		this.insertBoardCommand = insertBoardCommand;
		this.selectBoardByNoCommand = selectBoardByNoCommand;
		this.updateBoardCommand = updateBoardCommand;
		this.deleteBoardCommand = deleteBoardCommand;
		this.searchQueryCommand = searchQueryCommand;
	}


	// ????????? ??????????????????
	@PostMapping(value="selectBoardList.do",
				 produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> selectBoardList(@RequestBody QueryDTO queryDTO,
											   Model model) {
		model.addAttribute("queryDTO", queryDTO);
		return selectBoardListCommand.execute(sqlSession, model);
	}
	
	// ????????? ??????
	@PostMapping(value="insertBoard.do")
	public String insertBoard(MultipartHttpServletRequest multipartRequest,
							  Model model) {
		model.addAttribute("multipartRequest", multipartRequest);
		insertBoardCommand.execute(sqlSession, model);
		return "redirect:boardPage.do";
	}
	
	// ????????? ????????????
	@GetMapping(value="selectBoardByNo.do")
	public String selectBoardByNo(HttpServletRequest request,
								  Model model) {
		model.addAttribute("request", request);
		selectBoardByNoCommand.execute(sqlSession, model);
		return "board/viewBoard";
	}
	
	// ????????? ??????
	@PostMapping(value="updateBoard.do")
	public String updateBoard(MultipartHttpServletRequest multipartRequest,
							  Model model) {
		model.addAttribute("multipartRequest", multipartRequest);
		updateBoardCommand.execute(sqlSession, model);
		return "redirect:selectBoardByNo.do?bno=" + multipartRequest.getParameter("bno");
	}
	
	// ????????? ??????
	@PostMapping(value="deleteBoard.do")
	public String deleteBoard(MultipartHttpServletRequest multipartRequest,
							  Model model) {
		model.addAttribute("multipartRequest", multipartRequest);
		deleteBoardCommand.execute(sqlSession, model);
		return "redirect:boardPage.do";
	}

	// ??????
	@PostMapping(value="searchQuery.do",
				 produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> searchQuery(@RequestBody QueryDTO queryDTO,
											Model model) {
		model.addAttribute("queryDTO", queryDTO);
		return searchQueryCommand.execute(sqlSession, model);
	}
	
	
	
	
}
