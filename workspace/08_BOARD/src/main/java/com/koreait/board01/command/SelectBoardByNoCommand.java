package com.koreait.board01.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.koreait.board01.dao.BoardDAO;
import com.koreait.board01.dto.Board;

public class SelectBoardByNoCommand implements BoardCommand {

	@Override
	public void execute(Model model) {

		Map<String, Object> map = model.asMap();
		
		long no = (Long)map.get("no");
		
		model.addAttribute("board", BoardDAO.getInstance().selectBoardByNo(no));
		
	}

}
