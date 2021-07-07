package com.koreait.springproject.dao;

import java.util.List;

import com.koreait.springproject.dto.BoardDTO;
import com.koreait.springproject.dto.PageDTO;
import com.koreait.springproject.dto.QueryDTO;

public interface BoardDAO {
	
	// 페이징
	public int getTotalRecord();
	
	// 전체 게시글 반환
	public List<BoardDTO> selectBoardList(PageDTO pageDTO); 
	
	// 조회수 증가
	public int updateHit(long bno);
	
	// 게시글 작성
	public int insertBoard(String writer, String title, String content, String ip, String image);
	
	// 게시글 내용보기
	public BoardDTO selectBoardByNo(long bno);
	
	// 게시글 수정하기
	public int updateBoard(String title, String content, String image, long bno);
	
	// 게시글 삭제하기
	public int deleteBoard(long bno);
	
	
	
	// 게시글 검색
	public List<BoardDTO> searchQuery(QueryDTO queryDTO);
	
	// 검색 레코드 수
	public int getSearchRecord(QueryDTO queryDTO);
}
