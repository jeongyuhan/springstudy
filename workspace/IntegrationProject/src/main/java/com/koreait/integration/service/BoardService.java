package com.koreait.integration.service;

import java.util.List;
import java.util.Map;

import com.koreait.integration.domain.Board;

public interface BoardService {
	
	public List<Board> totalList(); // selectAll()
	
	public List<Board> searchList(Map<String, String> map); // selectQuery()
	
	
	
}
