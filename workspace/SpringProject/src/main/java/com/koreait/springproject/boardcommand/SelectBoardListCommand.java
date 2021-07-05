package com.koreait.springproject.boardcommand;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.springproject.dao.BoardDAO;
import com.koreait.springproject.dto.PageDTO;
import com.koreait.springproject.util.PagingUtils;

public class SelectBoardListCommand implements BoardCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		BoardDAO boardDAO = sqlSession.getMapper(BoardDAO.class);
		
		int totalRecord = boardDAO.getTotalRecord();
		
		PageDTO pageDTO = PagingUtils.getPage(totalRecord, page);
		String paging = PagingUtils.getPaging("selectBoardList.do", page);
		
		model.addAttribute("paging", paging);
		model.addAttribute("list", boardDAO.selectBoardList(pageDTO));
		
	}

}
