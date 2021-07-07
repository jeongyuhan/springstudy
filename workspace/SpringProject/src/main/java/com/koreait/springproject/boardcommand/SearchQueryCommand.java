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

public class SearchQueryCommand {
	
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		QueryDTO queryDTO = (QueryDTO)map.get("queryDTO");
		
		int page = queryDTO.getPage();
		
		BoardDAO boardDAO = sqlSession.getMapper(BoardDAO.class);
		
		int searchRecord = boardDAO.getSearchRecord(queryDTO);
		
		PageDTO paging = PagingUtils.getPage(searchRecord, page);
		
		int beginRecord = paging.getBeginRecord();
		int endRecord = paging.getEndRecord();
		
		queryDTO.setBeginRecord(beginRecord);
		queryDTO.setEndRecord(endRecord);
		
		List<BoardDTO> list = boardDAO.searchQuery(queryDTO);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", list);
		resultMap.put("exists", list.size() > 0);
		resultMap.put("paging", paging);
		return resultMap;
	}

}
