package com.koreait.contact04.command;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.contact04.dao.ContactDAO;
import com.koreait.contact04.dto.Contact;

public class UpdateContactCommand implements ContactCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		
		Contact contact = (Contact)map.get("contact");
		
		ContactDAO contactDAO = sqlSession.getMapper(ContactDAO.class);
		int result = contactDAO.updateContact(contact);
		
		HttpServletResponse response = (HttpServletResponse)map.get("response");
		response.setContentType("text/html; charset=utf-8");
		try {
			PrintWriter out = response.getWriter();

			if(result > 0) {
				out.println("<script>"); 			
				out.println("alert('수정 성공')"); 	
				out.println("location.href = 'selectContactByNo.do?no=" + contact.getNo() + "'");
				out.println("</script>"); 			
			} else {
				out.println("<script>"); 			
				out.println("alert('수정 실패')"); 		
				out.println("history.back"); 		
				out.println("</script>"); 		
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
