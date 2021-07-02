package com.koreait.springproject.boardcommand;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.springproject.dao.BoardDAO;

public class SelectBoardListCommand implements BoardCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {

		BoardDAO boardDAO = sqlSession.getMapper(BoardDAO.class);
		model.addAttribute("list", boardDAO.selectBoardList());
		
	}

}
