package com.koreait.springproject.boardcommand;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.springproject.dao.BoardDAO;
import com.koreait.springproject.dto.BoardDTO;
import com.koreait.springproject.dto.PageDTO;
import com.koreait.springproject.dto.QueryDTO;
import com.koreait.springproject.util.PagingUtils;

public class SearchCommand implements BoardCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String column = request.getParameter("column");
		String query = request.getParameter("query");
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		QueryDTO queryDTO = new QueryDTO();
		queryDTO.setColumn(column);
		queryDTO.setQuery(query);
		
		BoardDAO boardDAO = sqlSession.getMapper(BoardDAO.class);
		
		int searchRecord = boardDAO.getSearchRecord(queryDTO);
		
		PageDTO pageDTO = PagingUtils.getPage(searchRecord, page);
		
		queryDTO.setBeginRecord(pageDTO.getBeginRecord());
		queryDTO.setEndRecord(pageDTO.getEndRecord());
		
		List<BoardDTO> list = boardDAO.search(queryDTO);
		
		
		String paging = null;
		if(list.size() > 0) {
			paging = PagingUtils.getPaging("search.do?column=" + column + "&query=" + query, page);			
		}
				
		model.addAttribute("paging", paging);
		model.addAttribute("list", list);
		
	}

}
