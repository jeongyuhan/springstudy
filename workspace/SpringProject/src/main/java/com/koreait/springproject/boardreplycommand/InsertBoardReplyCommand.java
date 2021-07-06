package com.koreait.springproject.boardreplycommand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.springproject.dao.BoardReplyDAO;
import com.koreait.springproject.dto.BoardReplyDTO;

public class InsertBoardReplyCommand {

	
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
	
		Map<String, Object> resultMap = null;
		
		
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
			List<BoardReplyDTO> list = boardReplyDAO.selectReply();
			resultMap = new HashMap<String, Object>();		
			
			if(result > 0) {
				resultMap.put("status", 200);
				resultMap.put("list", list);
				resultMap.put("message", "댓글이 작성되었습니다.");
			} else {
				resultMap.put("status", 500);
				resultMap.put("list", null);
				resultMap.put("message", "댓글이 작성되지않았습니다.");				
			}
	
		return resultMap;
	}

}
