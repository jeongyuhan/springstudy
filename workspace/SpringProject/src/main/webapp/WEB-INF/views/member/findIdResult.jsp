<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	
	<h1>아이디 찾기 결과</h1>
	
	아이디 : ${findUser.id}<br>
	이름 : ${findUser.name}<br>
	이메일 : ${findUser.email}<br> 
	가입일 : ${findUser.regdate}<br><br>
	
	<input type="button" value="비밀번호 찾기" onclick="location.href='findPwPage.do'">
	<input type="button" value="로그인페이지로이동" onclick="location.href='index.do'">
</body>
</html>