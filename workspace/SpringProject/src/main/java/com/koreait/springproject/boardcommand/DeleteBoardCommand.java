package com.koreait.springproject.boardcommand;

import java.io.File;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.koreait.springproject.dao.BoardDAO;

public class DeleteBoardCommand implements BoardCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)map.get("multipartRequest");
		
		long bno = Long.parseLong(multipartRequest.getParameter("bno"));
		
		BoardDAO boardDAO = sqlSession.getMapper(BoardDAO.class);
		boardDAO.deleteBoard(bno);
		
		String filename1 = multipartRequest.getParameter("filename1");
		String realPath = multipartRequest.getServletContext().getRealPath("resources/archive");
		File file = new File(realPath, filename1); 	
		
		if(file != null) {
			if(file.exists()) {
				file.delete();
			}				
		}
		
	}

}
