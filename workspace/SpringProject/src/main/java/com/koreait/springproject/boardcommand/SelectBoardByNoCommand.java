package com.koreait.springproject.boardcommand;

import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.springproject.dao.BoardDAO;
import com.koreait.springproject.dto.BoardDTO;

public class SelectBoardByNoCommand implements BoardCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		long bno = Long.parseLong(request.getParameter("bno"));
		
		BoardDAO boardDAO = sqlSession.getMapper(BoardDAO.class);
		BoardDTO boardDTO = boardDAO.selectBoardByNo(bno);
		boardDAO.updateHit(bno);
		model.addAttribute("boardDTO", boardDTO);
		
		try { 
			model.addAttribute("image", URLDecoder.decode(boardDTO.getImage(), "utf-8")); 
		} catch(Exception e) { 
			e.printStackTrace();
		}
		
	}

}
