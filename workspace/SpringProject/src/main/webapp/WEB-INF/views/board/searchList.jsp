<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>

	<h1>검색 결과</h1>
	
	<table border="1">
		<thead>
			<tr>
				<td>글번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>좋아요</td>
				<td>작성일</td>
				<td>첨부자료</td>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty list}">
				<tr>
					<td colspan="6">검색결과가 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="${not empty list}">
				<c:forEach var="board" items="${list}">
					<tr>
						<td>${board.bno}</td>
						<td><a href="selectBoardByNo.do?bno=${board.bno}">${board.title}</a></td>
						<td>${board.writer}</td>
						<td>${board.hit}</td>
						<td>${board.postdate}</td>
						<td>${board.image}</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>	
		<tfoot>
			<tr>
				<td colspan="6">
					${paging}				
				</td>
			</tr>
		</tfoot>
	</table>
	
</body>
</html>