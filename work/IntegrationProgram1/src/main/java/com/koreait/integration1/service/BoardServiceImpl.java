package com.koreait.integration1.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.koreait.integration1.domain.SearchBoard;
import com.koreait.integration1.repository.BoardRepository;

public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository repository;
	
	@Override
	public List<SearchBoard> selectAll() {
		return repository.selectAll();
	}

	@Override
	public List<SearchBoard> selectQuery(Map<String, String> map) {
		return repository.selectQuery(map);
	}

}
