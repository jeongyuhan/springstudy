package com.koreait.springproject.dao;

import java.util.List;

import com.koreait.springproject.dto.BoardReplyDTO;
import com.koreait.springproject.dto.PageDTO;

public interface BoardReplyDAO {
	
	// 페이징
	public int getTotalRecord();
	
	// 댓글 작성
	public int insertBoardReply(BoardReplyDTO boardReplyDTO);
	
	// 댓글 리스트
	public List<BoardReplyDTO> selectReply(PageDTO pageDTO);
}	
