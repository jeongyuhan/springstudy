package com.koreait.springproject.boardreplycommand;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.springproject.dao.BoardReplyDAO;
import com.koreait.springproject.dto.BoardReplyDTO;

public class BoardReplyInsertCommand implements BoardReplyCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String replywriter = request.getParameter("replywriter");
		String replycontent = request.getParameter("replycontent");
		String replyip = request.getRemoteAddr();
		
		BoardReplyDTO boardReplyDTO = new BoardReplyDTO();
		boardReplyDTO.setReplywriter(replywriter);
		boardReplyDTO.setReplycontent(replycontent);
		boardReplyDTO.setReplyip(replyip);
		
		BoardReplyDAO boardReplyDAO = sqlSession.getMapper(BoardReplyDAO.class);
		
		int result = boardReplyDAO.insertBoardReply(boardReplyDTO);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("result", result);
		
		return resultMap;
	}

}
