package com.koreait.contact01.command;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.koreait.contact01.dao.ContactDAO;

public class DeleteContactCommand implements ContactCommand {

	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap();
		Long no = (Long)map.get("no");
		HttpServletResponse response = (HttpServletResponse)map.get("response");
		response.setContentType("text/html; charset=utf-8");
		
		int result = ContactDAO.getInstance().deleteContact(no);
		try {
			PrintWriter out = response.getWriter();
			
			if(result > 0) {
				out.println("<script>"); 			
				out.println("alert('삭제 성공')"); 	
				out.println("location.href = 'selectContactList.do'");
				out.println("</script>"); 			
			} else {
				out.println("<script>"); 			
				out.println("alert('삭제 실패')"); 		
				out.println("history.back"); 		
				out.println("</script>"); 		
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
