<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
		$(document).ready(function(){
			fn_update();
			fn_presentPwCheck();
			fn_updatePw();
		});
		function fn_update(){
			$('#update_btn').click(function(){
				if (confirm('수정할까요?')) {
					$('#f').attr('action', 'updateMember.do');
					$('#f').submit();					
				}
			});
		}
		// 현재 비밀번호 검사
		var presentPwPass = false;
		function fn_presentPwCheck(){
			$('#pw0').keyup(function(){
				var obj = {
					pw: $('#pw0').val()
				};
				$.ajax({
					url: 'presentPwCheck.do',
					type: 'post',
					contentType: 'application/json',
					data: JSON.stringify(obj),
					dataType: 'json',
					success: function(resultMap){
						console.log(resultMap.isCorrect);
						if (resultMap.isCorrect) {
							presentPwPass = true;
						} else {
							presentPwPass = false;
						}
					}
				});
			});
		}
		// 비밀번호 변경
		function fn_updatePw(){
			$('#pw_btn').click(function(){
				if (!presentPwPass) {
					alert('현재 비밀번호가 틀립니다.');
					$('#pw0').focus();
					return false;
				} else if ($('#pw').val() == '') {
					alert('비밀번호를 입력하세요.');
					$('#pw').focus();
					return false;
				} else if ($('#pw').val() != $('#pw2').val()) {
					alert('비밀번호 입력을 확인하세요.');
					return false;
				} else {
					$('#f').attr('action', 'updatePw.do');
					$('#f').submit();
				}
			});
		}
	</script>
</head>
<body>
	
	<h1>마이 페이지</h1>
	
	<form id="f" method="post">
		회원번호<br>
		${loginUser.no}<br><br>
		
		아이디<br>
		${loginUser.id}<br><br>
		
		현재 비밀번호<br>
		<input type="password" name="pw0" id="pw0"><br>
		
		새 비밀번호<br>
		<input type="password" name="pw" id="pw"><br>
	
		비밀번호 확인<br>
		<input type="password" name="pw2" id="pw2"><br>
		<input type="button" value="비밀번호 변경하기" id="pw_btn"><br><br>
		
		이름<br>
		<input type="text" name="name" id="name" value="${loginUser.no}"><br><br>
	
		이메일<br>
		<input type="text" name="email" id="email" value="${loginUser.email}"><br><br>
		
		<input type="hidden" name="no" value="${loginUser.no}">
		
		가입일<br>
		${loginUser.regdate}<br><br>

		<input type="button" value="정보 수정" id="update_btn">
		<input type="button" value="되돌아가기" onclick="location.href = 'index.do'">
	</form>
</body>
</html>