package com.koreait.springproject.membercommand;

import java.util.HashMap;
import java.util.Map;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.Model;

public class EmailAuthCommand {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public Map<String, String> execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String email = request.getParameter("email");
		String authCode = null;
		
		MimeMessage message = mailSender.createMimeMessage();
		try {
			message.setHeader("Content-Type", "text/plain; charset=utf-8");
			message.setFrom(new InternetAddress("yh30433583@gmail.com", "관리자"));  // 보내는 사람
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));  // 받는 사람
			message.setSubject("인증 요청 메일입니다.");
			authCode = "123456";  // 6자리 인증코드 (random 코드를 구현해도 되고, 암호화시킨 6자리 코드를 보내도 된다.(암호화는 디펜던시 추가))
			message.setText("인증번호는 " + authCode + "입니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mailSender.send(message);
		
		Map<String, String> resultMap = new HashMap<>();
		resultMap.put("authCode", authCode);
		return resultMap;
		
	}
	
}
