package com.koreait.file.command;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.koreait.file.dao.BoardDAO;

public class InsertBoardCommand implements BoardCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {

		// 파일 첨부가 들어간 request는 HttpServletRequest로는 받지 못하고, MultipartHttpServletRequest로 받아야 한다.
		Map<String, Object> map = model.asMap();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)map.get("multipartRequest");
		
		String writer = multipartRequest.getParameter("writer");
		String title = multipartRequest.getParameter("title");
		String content = multipartRequest.getParameter("content");
		List<MultipartFile> files = multipartRequest.getFiles("files");
		/*
			<input type="file" name="filename"> : 단일 파일 첨부 
			MultipartFile file = multipartRequest.getFile("filename");
		 */
		/*	
			<input type="file" name="files" multiple> : 다중 파일 첨부
			List<MultipartFile> files = multipartRequest.getFiles("files");
		 */
		
		BoardDAO boardDAO = sqlSession.getMapper(BoardDAO.class);
		
		for(MultipartFile file : files) {
			if(file != null && !file.isEmpty()) { // NullpointException 방지를 위해 if문을 사용
				// 올릴 때 파일명
				String originalFilename = file.getOriginalFilename();
				// 첨부된 파일명 확인
				System.out.println("첨부 파일명 : " + originalFilename);
				// 서버에 저장할 파일명
				// 파일명의 중복 방지 대책이 필요
				// 파일명_올린시간.확장자
				String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1); // 확장자 구하기
				String filename = originalFilename.substring(0, originalFilename.lastIndexOf(".")); // 파일 이름 구하기
				String uploadFilename = filename + "_" + System.currentTimeMillis() + "." + extension; // 업로드되는 파일 이름 구하기
				
				// 첨부파일을 저장할 서버 위치
				String realPath = multipartRequest.getServletContext().getRealPath("resources/archive"); // 현재 archive 디렉터리는 없는 상태이므로 생성이 필요하다.
				
				// archive 디렉터리 생성
				File archive = new File(realPath);
				if(!archive.exists()) {
					archive.mkdirs();
				}
				
				// 서버에 첨부파일 저장
				File attach = new File(archive, uploadFilename);
				try {
					file.transferTo(attach);					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				// DB에 데이터 저장
				boardDAO.insertBoard(writer, title, content, uploadFilename);
				
			} else {
				
				// DB에 데이터 저장
				boardDAO.insertBoard(writer, title, content, ""); // 첨부파일이 없는 경우
				
			}
		} // for (위의 형식에서 for문을 제외하고 작성하면 단일첨부 처리가 된다.)
		
	}

}
