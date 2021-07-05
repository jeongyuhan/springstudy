package com.koreait.springproject.boardcommand;

import java.io.File;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.koreait.springproject.dao.BoardDAO;

public class InsertBoardCommand implements BoardCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)map.get("multipartRequest");
		
		String title = multipartRequest.getParameter("title");
		String writer = multipartRequest.getParameter("writer");
		String content = multipartRequest.getParameter("content");
		String ip = multipartRequest.getRemoteAddr();
		MultipartFile image = multipartRequest.getFile("image");
		
		BoardDAO boardDAO = sqlSession.getMapper(BoardDAO.class);
		
		if(image != null && !image.isEmpty()) {
			String originalFilename = image.getOriginalFilename();
			String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1); 
			String filename = originalFilename.substring(0, originalFilename.lastIndexOf(".")); 
			String uploadFilename = filename + "_" + System.currentTimeMillis() + "." + extension; 
			
			String realPath = multipartRequest.getServletContext().getRealPath("resources/archive");
			
			File archive = new File(realPath);
			if(!archive.exists()) {
				archive.mkdirs();
			}
			
			File attach = new File(archive, uploadFilename);
			try {
				image.transferTo(attach);					
			} catch (Exception e) {
				e.printStackTrace();
			}	
			try {
				uploadFilename = URLEncoder.encode(uploadFilename, "UTF-8");
			} catch (Exception e) { 
				e.printStackTrace();
			}		
			boardDAO.insertBoard(writer, title, content, ip, uploadFilename);
			
		} else {
			boardDAO.insertBoard(writer, title, content, ip, "");			
		}
		
	}

}
