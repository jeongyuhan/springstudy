package com.koreait.springproject.boardreplycommand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.springproject.dao.BoardReplyDAO;
import com.koreait.springproject.dto.BoardReplyDTO;
import com.koreait.springproject.dto.PageDTO;
import com.koreait.springproject.dto.QueryDTO;
import com.koreait.springproject.util.PagingUtils;

public class SelectAllReplyCommand implements BoardReplyCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		QueryDTO queryDTO = (QueryDTO)map.get("queryDTO");
		
		int page = queryDTO.getPage();
		
		BoardReplyDAO boardReplyDAO = sqlSession.getMapper(BoardReplyDAO.class);
		
		int totalRecord = boardReplyDAO.getTotalRecord();
		
		PageDTO paging = PagingUtils.getPage(totalRecord, page);
		
		List<BoardReplyDTO> list = boardReplyDAO.selectReply(paging);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", list);
		resultMap.put("exists", list.size() > 0);
		resultMap.put("paging", paging);
		
		return resultMap;
	}

}
