package com.koreait.springproject.boardcommand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.springproject.dao.BoardDAO;
import com.koreait.springproject.dto.BoardDTO;
import com.koreait.springproject.dto.PageDTO;
import com.koreait.springproject.dto.QueryDTO;
import com.koreait.springproject.util.PagingUtils;

public class SelectBoardListCommand {

	public Map<String, Object> execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		QueryDTO queryDTO = (QueryDTO)map.get("queryDTO");
		
		int page = queryDTO.getPage();		
		
		BoardDAO boardDAO = sqlSession.getMapper(BoardDAO.class);

		int totalRecord = boardDAO.getTotalRecord();
		
		PageDTO paging = PagingUtils.getPage(totalRecord, page);
		
		List<BoardDTO> list = boardDAO.selectBoardList(paging);

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("list", list);
		resultMap.put("exists", list.size() > 0);
		resultMap.put("paging", paging);
		return resultMap;
	}

}
