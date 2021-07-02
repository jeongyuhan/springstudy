package com.koreait.springproject.dao;

import java.util.List;

import com.koreait.springproject.dto.BoardDTO;

public interface BoardDAO {
	
	// 전체 게시글 반환
	public List<BoardDTO> selectBoardList(); 
	
	public int insertBoard(String writer, String title, String content, String ip, String image);
	
	
	
	
	
	
	
	
	
}
