package com.koreait.mvc03.quiz;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuizController {

	@RequestMapping("quiz/v01")
	public String a(HttpServletRequest request, Model model) {
		String title = request.getParameter("title");
		int hit = Integer.parseInt(request.getParameter("hit"));
		
		model.addAttribute("title", title);
		model.addAttribute("hit", hit);
		model.addAttribute("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		
		return "quiz/view01";
	}
	
	@RequestMapping("quiz/v02")
	public String b(@RequestParam("title") String title,
					@RequestParam("hit") int hit,
					Model model) {
		model.addAttribute("title", title);
		model.addAttribute("hit", hit);
		model.addAttribute("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		
		return "quiz/view02";
	}
	
	@RequestMapping("quiz/v03")
	public String c(@RequestParam(value="title", required=false) String title,
					@RequestParam(value="hit", required=false, defaultValue="0") int hit,
					Model model) {
		
		model.addAttribute("title", title);
		model.addAttribute("hit", hit);
		model.addAttribute("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		
		return "quiz/view03";
	}
	
	@RequestMapping("quiz/v04")
	public String d(Board board, Model model) {
		board.setDate(new Date());
		model.addAttribute("board", board);
		return "quiz/view04";
	}
	
	@RequestMapping("quiz/v05")
	public String e(@ModelAttribute(value="title") String title,
					@ModelAttribute(value="hit") int hit,
					Model model) {
		model.addAttribute("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

		
		return "quiz/view05";
	}
	
	@RequestMapping("quiz/v06")
	public String f(@ModelAttribute(value="board") Board board, Model model) {
		model.addAttribute("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

		return "quiz/view06";
	}
	
}
