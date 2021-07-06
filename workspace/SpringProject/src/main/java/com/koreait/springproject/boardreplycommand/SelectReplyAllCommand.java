package com.koreait.springproject.boardreplycommand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.springproject.dao.BoardDAO;
import com.koreait.springproject.dao.BoardReplyDAO;
import com.koreait.springproject.dto.BoardReplyDTO;
import com.koreait.springproject.dto.PageDTO;
import com.koreait.springproject.util.PagingUtils;

public class SelectReplyAllCommand implements BoardReplyCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		BoardReplyDAO boardReplyDAO = sqlSession.getMapper(BoardReplyDAO.class);
		Map<String, Object> resultMap = new HashMap<>();
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));		
		
		int totalRecord = boardReplyDAO.getTotalRecord();
		
		PageDTO pageDTO = PagingUtils.getPage(totalRecord, page);
		String paging = PagingUtils.getPaging("selectAllReply.do", page);
		
		model.addAttribute("paging", paging);
		List<BoardReplyDTO> list = boardReplyDAO.selectReply(pageDTO);
		
		if(list.size() == 0) {
			resultMap.put("status", 500);
			resultMap.put("list", null);
			resultMap.put("message", "댓글을 달아주세요.");
		} else {
			resultMap.put("status", 200);
			resultMap.put("list", list);
			resultMap.put("message", "댓글이 있습니다.");				
		}
		
	}

}
