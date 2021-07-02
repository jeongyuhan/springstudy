<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
		$(document).ready(function(){
			fn_insertBoard();
		})
		
		function fn_insertBoard(){
			$('#insert_board_btn').click(function(){
				location.href = 'insertBoardPage.do';
			});
		}
	</script>	
</head>
<body>
	
	<h1>갤러리 게시판</h1>
	<c:if test="${loginUser != null}">			
		<input type="button" value="게시글 작성하기" id="insert_board_btn">
	</c:if>
	
	<br><br><hr><br><br>
	
	<div class="list_box">
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
						<td colspan="6">작성된 게시글이 없습니다.</td>
					</tr>
				</c:if>
				<c:if test="${not empty list}">
					<c:forEach var="board" items="${list}">
						<tr>
							<td>${board.bno}</td>
							<td><a href="selectBoardByNo.do?no=${board.bno}">${board.title}</a></td>
							<td>${board.writer}</td>
							<td>${board.hit}</td>
							<td>${board.postdate}</td>
							<td>
								<c:if test="${not empty board.image}">
									${board.image}"<i class="fas fa-paperclip"></i>						
								</c:if>
							</td>
							<c:if test="${loginUser.id == board.writer}">
								<td><input type="button" value="수정" id="board_update_btn"></td>		
								<td><input type="button" value="삭제" id="board_delete_btn"></td>		
							</c:if>
						</tr>
					</c:forEach>
				</c:if>
				
				
			</tbody>
		</table>
	</div>
	
</body>
</html>