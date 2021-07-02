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
			fn_id_check();			
			fn_pw_check();
			fn_pw_check2();
			fn_verify_num();
			fn_join();
		})
		
		// 아이디 중복 검사
		var idPass = false;
		function fn_id_check(){
			$('#id').keyup(function(){
				$.ajax({
					url: 'idCheck.do',
					type: 'get',
					data: 'id=' + $('#id').val(),
					dataType: 'json',
					success: function(resultMap){
						if(resultMap.count == 0) {
							$('#id_check').text('가입이 가능한 아이디입니다.').css('color', 'green');
							idPass = true;
						} else {
							$('#id_check').text('이미 사용중인 아이디입니다.').css('color', 'red');
							idPass = false;							
						}
					}
				}); 
			});
		}
		
		// 비밀번호 검사(정규식 검사)
		var pwPass = false;
		function fn_pw_check(){
			$('#pw').keyup(function(){
				var regPW = /^[0-9]{1,4}$/;
				if(regPW.test($('#pw').val())) {
					$('#pw_check').text('사용가능한 비밀번호입니다.').css('color', 'green');
					pwPass = true;
				} else {
					$('#pw_check').text('비밀번호는 0~9까지의 숫자 1~4자리를 입력해주세요.').css('color', 'red');
					pwPass = false;					
				}
			});
		}
		
		// 비밀번호 검사(비밀번호 == 비밀번호 확인)
		var pwPass2 = false;
		function fn_pw_check2(){
			$('#pw2').keyup(function(){
				if($('#pw').val() == $('#pw2').val()) {
					$('#pw2_check').text('비밀번호가 일치합니다.').css('color', 'green');
					pwPass2 = true;
				} else {
					$('#pw2_check').text('비밀번호가 일치하지 않습니다.').css('color', 'red');
					pwPass2 = false;					
				}
			});
		}
		
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
		
		// 회원가입
		function fn_join(){
			$('#join_btn').click(function(){
				if(!idPass){
					alert('아이디를 입력해주세요.');
					$('#id').focus();
					return false;
				} else if (!pwPass || !pwPass2) {
					alert('비밀번호를 확인해주세요.');
					return false;
				} else if (!authPass) {
					alert('이메일 인증을 확인해주세요.');
					return false;
				} else {
					$('#f').attr('action', 'join.do');					
					$('#f').submit();
				}
			});
		}
	</script>	
</head>
<body>
	
	<h1>회원가입</h1>
	
	<form id="f" method="post">
		<label for="id">아이디</label><br>
		<input type="text" id="id" name="id"><br>
		<span id="id_check"></span><br><br>
		
		<label for="pw">비밀번호</label><br>
		<input type="password" id="pw" name="pw"><br>
		<span id="pw_check"></span><br><br>
		
		<label for="pw2">비밀번호 확인</label><br>
		<input type="password" id="pw2"><br>
		<span id="pw2_check"></span><br><br>
		
		<label for="id">이름</label><br>
		<input type="text" id="name" name="name"><br><br>
		
		<label for="id">전화번호</label><br>
		<input type="text" id="phone" name="phone"><br><br>

		<label for="id">주소</label><br>
		<input type="text" id="address" name="address"><br><br>
		
		<label for="id">이메일</label><br>
		<input type="text" id="email" name="email"><br>
		<input type="button" value="인증번호받기" id="verify_num_btn"><br>
		<input type="text" name="user_key" id="user_key"><br>
		<input type="button" value="인증하기" id="verify_btn"><br><br>
		
		
		<input type="button" value="가입하기" id="join_btn">
	</form>
	
</body>
</html>