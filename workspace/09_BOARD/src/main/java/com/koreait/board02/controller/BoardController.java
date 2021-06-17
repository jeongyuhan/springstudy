package com.koreait.board02.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.board02.command.DeleteBoardCommand;
import com.koreait.board02.command.InsertBoardCommand;
import com.koreait.board02.command.SelectBoardByNoCommand;
import com.koreait.board02.command.SelectBoardListCommand;
import com.koreait.board02.command.UpdateBoardCommand;
import com.koreait.board02.dto.Board;

//@Controller
public class BoardController {
	
	// field
	private SelectBoardListCommand selectBoardListCommand;
	private SelectBoardByNoCommand selectBoardByNoCommand;
	private UpdateBoardCommand updateBoardCommand;
	private DeleteBoardCommand deleteBoardCommand;
	private InsertBoardCommand	insertBoardCommand;
	
	@Autowired
	public void setCommand(SelectBoardListCommand selectBoardListCommand, 
						   SelectBoardByNoCommand selectBoardByNoCommand,
						   UpdateBoardCommand updateBoardCommand,
						   DeleteBoardCommand deleteBoardCommand,
						   InsertBoardCommand	insertBoardCommand) {
		this.selectBoardListCommand = selectBoardListCommand;
		this.selectBoardByNoCommand = selectBoardByNoCommand;
		this.updateBoardCommand = updateBoardCommand;
		this.deleteBoardCommand = deleteBoardCommand;
		this.insertBoardCommand = insertBoardCommand;
	}
	
	@GetMapping(value="/")
	public String index() {
		return "index"; // index.jsp로 forward
	}
	
	@GetMapping(value="selectBoardList.do")
	public String selectBoardList(Model model) {
		selectBoardListCommand.execute(model);
		return "board/list"; // board/list.jsp로 forward (model.addAttribute 처리한 값이 속성으로 넘어간다.)
	}
	
	@GetMapping(value="selectBoardByNo.do")
	public String selectBoardByNo(@RequestParam("no") long no,
								  Model model) {
		model.addAttribute("no", no); // SelectBoardByNoCommand에 no를 넘겨주기 위해서
		selectBoardByNoCommand.execute(model);
		return "board/view"; // board/view.jsp forward (selectBoardByNoCommand가 model에 저장한 board를 가지고 이동)		
	}
	
	@PostMapping(value="updateBoardPage.do")
	public String updateBoardPage(Board board, Model model) {
		model.addAttribute("board", board);
		return "board/update"; // board/update.jsp forward
	}
	
	@PostMapping(value="updateBoard.do")
	public String updateBoard(HttpServletRequest request, // update.jsp에서 전달한 파라미터
							  Model model) {
		model.addAttribute("req", request); // UpdateBoardCommand 에게 전달하기 위해서
		updateBoardCommand.execute(model);
		return "redirect:selectBoardByNo.do?no=" + request.getParameter("no"); // selectBoardList.do 매핑으로 리다이렉트 한다.(삽입, 수정, 삭제는 redirect)
	}
	
	@GetMapping(value="deleteBoard.do") // location.href=''; 를 통한 이동방식은 @GetMapping으로 처리한다. 
	public String deleteBoard(@RequestParam("no") long no, // 삭제할 게시글의 no
							  Model model) {
		model.addAttribute("no", no); // DeleteBoardCommand에게 전달하기 위해서
		deleteBoardCommand.execute(model);
		return "redirect:selectBoardList.do"; // 삭제 후 목록보기로 이동
		// return selectBoardList(model); 도 가능하다.
	}
	
	@GetMapping(value="insertBoardPage.do")
	public String insertBoardPage() {
		return "board/insert";
	}
	
	@GetMapping(value="insertBoard.do")
	public String insertBoard(Board board, Model model) {
		model.addAttribute("board", board); // InsertBoardCommand에 전달하기 위해서
		insertBoardCommand.execute(model);
		return "redirect:selectBoardList.do";
	}
	
	
	
	
	
	
	
}
