<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	
	<h1>view03.jsp</h1>

	제목 : ${param.title}<br>
	조회수 : ${param.hit}<br>
	작성일 : ${date}<br>

	제목 : ${title}<br>
	조회수 : ${hit}<br>
	작성일 : ${date}<br>

</body>
</html>