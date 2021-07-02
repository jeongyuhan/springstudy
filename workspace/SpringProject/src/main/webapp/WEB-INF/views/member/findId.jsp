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
			fn_verify_num();
			fn_findId();
		})
		
		// 이메일 인증 (인증번호 받기)
		function fn_verify_num(){
			$('#verify_num_btn').click(function(){
				if($('#emial').val() == '') {
					alert('이메일을 입력하세요.');
					$('#emial').focus();
					return false;
				}
				$.ajax({
					url: 'verifyNum.do',
					type: 'get',
					data: 'email=' + $('#email').val(),
					dataType: 'json',
					success: function(resultMap){
						alert('인증코드가 발송되었습니다.');
						fn_verify(resultMap.authCode);
					}
				});
			});
		}
		
		// 이메일 인증 (인증번호 검사)
		var authPass = false;
		function fn_verify(authCode){
			$('#verify_btn').click(function(){
				if(authCode == $('#user_key').val()){
					alert('인증되었습니다.');
					authPass = true;
				} else {
					alert('잘못된 입력입니다.');
					authPass = false;
				}
			});
		}
		
		function fn_findId(){
			$('#find_id_btn').click(function(){
				if($('#email').val() == '') {
					alert('이메일을 입력해주세요.');
					$('#email').focus();
					return false;
				} else if (!authPass) {
					alert('이메일 인증을 확인해주세요.');
					return false; 
				} else {
					$('#f').attr('action', 'findId.do');
					$('#f').submit();
				}
			});
		}
	</script>	
</head>
<body>
	
	<h1>아이디 찾기</h1>
	
	<form method="post" id="f">
		<label for="id">가입 당시 이메일</label><br>
		<input type="text" id="email" name="email"><br>
		<input type="button" value="인증번호받기" id="verify_num_btn"><br>
		<input type="text" name="user_key" id="user_key"><br>
		<input type="button" value="인증하기" id="verify_btn"><br><br>
		<input type="button" value="아이디 찾기" id="find_id_btn">
		<input type="button" value="로그인페이지로이동" onclick="location.href='index.do'">		
	</form>
	
</body>
</html>