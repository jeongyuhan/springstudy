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
			$('#f').submit(function(event){
				if('${contact.name}' == $('#name').val() && '${contact.tel}' == $('#tel').val() && '${contact.addr}' == $('#addr').val() &&
				   '${contact.email}' == $('#email').val() && '${contact.note}' == $('#note').val()){
					alert('수정할 내용이 없습니다.');
					event.preventDefault();
					return false;
				}
			})
			
			$('#delete_btn').click(function(){
				if(confirm('삭제하시겠습니까?')){
					location.href = 'deleteContact.do?no=${contact.no}';
				}
			})
			
			$('#list_btn').click(function(){
				location.href = 'selectContactList.do';
			})
			
		})
	</script>
</head>
<body>
	
	<h3>연락처 보기</h3>
	<form action="updateContact.do" id="f" method="post">
		<input type="hidden" name="no" id="no" value="${contact.no}">
 		이름<br>
		<input type="text" name="name" id="name" value="${contact.name}"><br><br>	
		전화<br>
		<input type="text" name="tel" id="tel" value="${contact.tel}"><br><br>	
		주소<br>
		<input type="text" name="addr" id="addr" value="${contact.addr}"><br><br>	
		이메일<br>
		<input type="text" name="email" id="email" value="${contact.email}"><br><br>	
		비고<br>
		<input type="text" name="note" id="note" value="${contact.note}"><br><br>	
		<button>수정하기</button>
		<input type="button" id="delete_btn" value="삭제하기">
		<input type="button" id="list_btn" value="전체연락처">
	</form>
</body>
</html>