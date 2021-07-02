<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	
	<h1>게시글 작성</h1>
	
	<div class="insert_box">
		<form action="insertBoard.do" method="post" enctype="multipart/form-data">
			<label for="title">제목</label>
			<input type="text" id="title" name="title"><br><br>
			<label for="writer">작성자</label>
			<input type="text" id="writer" value="${loginUser.id}" name="writer" readonly><br><br>
			<label for="content">내용</label>
			<textarea id="content" name="content"></textarea><br><br>
			<!-- <input type="text" id="content" name="content"> -->
			<label for="image">이미지 첨부하기</label>
			<input type="file" id="image" name="image"><br><br>
			
			<button>작성하기</button>
		</form>
	</div>
	
	
</body>
</html>