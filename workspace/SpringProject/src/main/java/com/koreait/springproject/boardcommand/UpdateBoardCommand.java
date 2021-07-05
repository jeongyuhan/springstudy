package com.koreait.springproject.boardcommand;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.koreait.springproject.dao.BoardDAO;

public class UpdateBoardCommand implements BoardCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)map.get("multipartRequest");
		
		long bno = Long.parseLong(multipartRequest.getParameter("bno"));
		String title = multipartRequest.getParameter("title");
		String content = multipartRequest.getParameter("content");
		String filename1 = multipartRequest.getParameter("filename1");
		MultipartFile filename2 = multipartRequest.getFile("filename2");
		
		String realPath = multipartRequest.getServletContext().getRealPath("resources/archive");
		
		File file = new File(realPath, filename1);
		
		BoardDAO boardDAO = sqlSession.getMapper(BoardDAO.class);
		
		if(filename2 != null && !filename2.isEmpty()) {
			if(file != null) {
				if(file.exists()) {
					file.delete();
				}
			}
			
			String originalFilename = filename2.getOriginalFilename();
			String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
			String filename = originalFilename.substring(0, originalFilename.lastIndexOf("."));
			String uploadFilename = filename + "_" + System.currentTimeMillis() + "." + extension;
			File uploadFile = new File(realPath, uploadFilename);
			try {				
				filename2.transferTo(uploadFile);
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			try {
				uploadFilename = URLEncoder.encode(uploadFilename, "UTF-8");	
			} catch(UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			boardDAO.updateBoard(title, content, uploadFilename, bno);
			
		} else {
			
			boardDAO.updateBoard(title, content, filename1, bno);
			
		}
	}

}
