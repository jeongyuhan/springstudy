<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	
	이름 : ${param.name}<br>
	나이 : ${param.age}<br>
	
	<h3>RedirectAttributes 인터페이스의 add FlashAttribute()로 저장하면 속성으로 저장된다.</h3>
	이름 : ${name}<br>
	나이 : ${age}<br>
</body>
</html>