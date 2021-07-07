<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
		$(document).ready(function(){
			// 게시판 돌아가기
			fn_return_list();
			// 게시글 수정
			fn_update();
			// 게시글 삭제
			fn_delete();
		})
		
		// 게시판 돌아가기
		function fn_return_list(){
			$('#return_list_btn').click(function(){
				location.href = 'boardPage.do';
			});
		}
		
		// 게시글 수정
		function fn_update(){
			$('#update_btn').click(function(event){
				if('${boardDTO.title}' == $('#title').val() && '${boardDTO.content}' == $('#content').val()
				   && $('#filename2').val() == '') {
					alert('수정할 내용이 없습니다.');
					event.preventDefault();
					return false;
				} else {
					$('#f').attr('action', 'updateBoard.do');
					$('#f').submit();
				}
			});
		}
		
		// 게시글 삭제
		function fn_delete(){
			$('#delete_btn').click(function(){
				if (confirm('삭제할까요?')) {
					$('#f').attr('action', 'deleteBoard.do');
					$('#f').submit();
				}
			});
		}
		
	</script>

</head>
<body>
	
	<h1>게시물 보기</h1>
	
	<div class="board_view_box">
		
		<form id="f" method="post" enctype="multipart/form-data">
			<input type="hidden" name="bno" value="${boardDTO.bno}">
			<input type="hidden" name="filename1" value="${image}">  <!-- 서버에 첨부된 첨부파일명 -->

			<label for="writer">작성자</label><br>
			<input type="text" id="writer" value="${boardDTO.writer}" readonly><br><br>
			
			<label for="ip">작성자 IP</label><br>
			<input type="text" id="ip" value="${boardDTO.ip}" readonly><br><br>

			<label for="hit">좋아요</label><br>
			<input type="text" id="hit" value="${boardDTO.hit}" readonly><br><br>
			
			<label for="postdate">작성일</label><br>
			<input type="text" id="postdate" value="${boardDTO.postdate}" readonly><br><br>
			
			<label for="lastmodified">최종수정일</label><br>		
			<input type="text" id="lastmodified" value="${boardDTO.lastmodified}" readonly><br><br>
			
			<label for="title">제목</label><br>
			<input type="text" id="title" name="title" value="${boardDTO.title}"><br><br>
			
			<label for="content">내용</label><br>
			<input type="text" id="content" name="content" value="${boardDTO.content}"><br><br>
			
			<label for="filename2">첨부 변경</label><br>
			<input type="file" id="filename2" name="filename2"><br><br>
			
			<label for="image">첨부 이미지</label><br>
			<img alt="${image}" id="image" src="resources/archive/${image}" style="width: 300px;"><br><br>
			
			<c:if test="${loginUser.id == boardDTO.writer}">
				<input type="button" value="수정하기" id="update_btn">
				<input type="button" value="삭제하기" id="delete_btn">
			</c:if>
			<input type="button" value="목록가기" id="return_list_btn"><br><br>
		</form>
	</div>
	
	
</body>
</html>