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
			fn_login();
			fn_delete_member();
		})
		
		function fn_login(){
			$('#f').submit(function(event){
				if($('#id').val() == '' || $('#pw').val() == ''){
					alert('아이디와 비밀번호를 입력하세요.');
					event.preventDefault();
					return false;
				}
			});
		}
		
		function fn_delete_member(){
			$('#delete_member').click(function(){
				if(confirm('정말 탈퇴하시겠습니까?')){
					location.href = "deleteMember.do";
				}
			});
		}
	</script>
	<style>
		#delete_member:hover {
			cursor: pointer;
		}
	</style>
</head>
<body>
	
	<h1>메인 페이지!</h1>
	<c:if test="${loginUser != null}">
		${loginUser.name} 님 반갑습니다!<br>
		<a href="logout.do">로그아웃</a>&nbsp;&nbsp;&nbsp;
		<a id="delete_member">회원탈퇴</a>
	</c:if>
	<c:if test="${loginUser == null}">
		<form action="login.do" method="post" id="f">
			아이디<br>
			<input type="text" name="id" id="id"><br><br>
			비밀번호<br>
			<input type="password" name="pw" id="pw"><br><br>
			<button>로그인</button>
		</form>
		<br>
		<a href="joinPage.do">회원가입</a>&nbsp;&nbsp;&nbsp;
		<a href="findIdPage.do">아이디 찾기</a>&nbsp;&nbsp;&nbsp;
		<a href="findPwPage.do">비밀번호 찾기</a>
	</c:if>
	
	<br><hr><br>
	
	<a href="selectBoardList.do">갤러리 게시판으로~</a>
	
</body>
</html>