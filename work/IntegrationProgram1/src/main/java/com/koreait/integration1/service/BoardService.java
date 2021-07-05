package com.koreait.integration1.service;

import java.util.List;
import java.util.Map;

import com.koreait.integration1.domain.SearchBoard;

public interface BoardService {

	public List<SearchBoard> selectAll();
	
	public List<SearchBoard> selectQuery(Map<String, String> map);
	
}
