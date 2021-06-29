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
			fn_selectMemberList();
			fn_paging();
			fn_selectMemberByNo();
			fn_insertMember();
			fn_updateMember();
			fn_deleteMember();
			fn_init();
		});
		
		// 1. 회원 목록
		var page = 1; // 전역 변수 page는 페이지를 클릭할 때 fn_paging()에 의해서 값이 변한다.
		function fn_selectMemberList(){
			var obj = {
					page: page
			};
			$.ajax({
				url: 'selectMemberList.do',
				type: 'post',
				contentType: 'application/json', 
				data: JSON.stringify(obj),
				dataType: 'json',
				success: function(resultMap){
					// 1. 목록 만들기
					$('#member_list').empty(); // 기존 회원 목록 초기화(초기화를 안하면 같은 데이터가 쌓인다.)
					if(resultMap.exists) {
						// resultMap.list 출력
						$.each(resultMap.list, function(i, member){
							$('<tr>')
							.append($('<td>').text(member.id)) // == .append('<td>').text(member.id)
							.append($('<td>').text(member.name))
							.append($('<td>').text(member.address))
							.append($('<td>').text(member.gender))
							.append($('<td>').html('<input type="hidden" name="no" id="no" value="' + member.no + '"><input type="button" value="조회" id="view_btn">'))
							.appendTo('#member_list');
						});					
					} else {
						$('<tr>')
						.append('<td colspan="5">등록된 회원이 없습니다.</td>')
						.appendTo('#member_list');
					}
					
					// 2. 페이징 만들기
					var paging = resultMap.paging;
					$('#paging').empty(); // 기존 페이지 영역 초기화
					// 1) 이전('◀')
					if(paging.beginPage <= paging.pagePerBlock) { // 이전('◀')이 없음(1블록)
						// class
						// 1. disable : 링크 없다. color silver.
						$('<div>').addClass('disable').text('◀').appendTo('#paging');
					} else { // 이전이 있음
						// class
						// 1. previous_block : click이벤트에서 활용 
						// 2. link : cursor pointer 사용
						$('<div>')
						.addClass('previous_block').addClass('link')
						.attr('data-page', paging.beginPage - 1)
						.text('◀')
						.appendTo('#paging');
					}
					// 2) 1 2 3 4 5 
					for(let p = paging.beginPage; p <= paging.endPage; p++) {
						if(p == paging.page) { // 표시된 페이지가 현재 페이지(링크가없다.)
							// class
							// 1. now_page : color limegreen
							$('<div>')
							.addClass('now_page')
							.text(p)
							.appendTo('#paging');
						} else { 
							// class
							// 1. go_page : click이벤트에서 활용
							// 2. link : cursor pointer 사용
							$('<div>')
							.addClass('go_page').addClass('link')
							.attr('data-page', p)
							.text(p)
							.appendTo('#paging');
						}
					}
					// 3) 다음('▶')
					if(paging.endPage == paging.totalPage) { // 다음('▶')이 없음. 마지막 블록
						// class
						// 1. disable : 링크 없다. color silver.
						$('<div>')
						.addClass('disable')
						.text('▶')
						.appendTo('#paging');
					} else { // 다음('▶')이 있음
						// class
						// 1. previous_block : click이벤트에서 활용 
						// 2. link : cursor pointer 사용
						$('<div>')
						.addClass('next_block').addClass('link')
						.attr('data-page', paging.endPage + 1)
						.text('▶')
						.appendTo('#paging');
					}
				}
			});
		}
		
		// 2. 회원 목록 페이징 (페이징의 링크 처리)
		function fn_paging() {
			$('body').on('click', '.previous_block', function(){
				page = $(this).attr('data-page'); // 위에 잡아둔 전역변수 page의 값을 바꿔준다.
				fn_selectMemberList(); // 바뀐 page를 가지고 목록을 반환하는 fn_selectMemberList() 함수를 실행시킨다.
			});
			$('body').on('click', '.go_page', function(){
				page = $(this).attr('data-page');
				fn_selectMemberList();
			});
			$('body').on('click', '.next_block', function(){
				page = $(this).attr('data-page');
				fn_selectMemberList();
			});
		}
		
		// 3. 회원 정보 보기
		function fn_selectMemberByNo(){
			$('body').on('click', '#view_btn', function(){ // $('#view_btn').click 을 사용하면 실행이 안된다.
				$('input:text[name="id"]').attr('readonly', true); // 아이디는 수정 불가능하게 한다.
				var obj = {
						no: $(this).prev().val() // 이전 형제로 만든 hidden의 no 값을 사용한다.
					 // no: $(this).siblings('#no').val()
				};
				$.ajax({
					url: 'selectMemberByNo.do',
					type: 'post',
					contentType: 'application/json',
					data: JSON.stringify(obj),
					dataType: 'json',
					success: function(resultMap){
						if(resultMap.exists) {
							$('input:text[name="id"]').val(resultMap.member.id);
							$('input:text[name="name"]').val(resultMap.member.name);
							$('input:text[name="address"]').val(resultMap.member.address);
							$('input:radio[name="gender"][value="' + resultMap.member.gender + '"]').prop('checked', true);
							$('#view_area input:hidden[name="no"]').val(resultMap.member.no);
						} else {
							alert(obj.no + '번 회원정보가 없습니다.');	
						}
					}
				})
			}); 
		}
		
		// 4. 회원 삽입
		function fn_insertMember(){
			$('#insert_btn').click(function(){
				var member = {
					id: $('#id').val(),
					name: $('#name').val(),
					address: $('#address').val(),
					gender: $('input:radio[name="gender"]:checked').val()
				}; 
				$.ajax({
					url: 'insertMember.do',
					type: 'post',
					contentType: 'application/json',
					data: JSON.stringify(member), // member에 저장된 데이터들을 JSON으로 변환시켜 전달
					dataType: 'json',
					success: function(resultMap){
						if(resultMap.result > 0) {
							alert('새로운 회원이 등록되었습니다.');
							fn_selectMemberList(); // 등록된 회원이 나올 수 있도록 목록을 다시 실행한다.
						}
					},
					error: function(xhr, testStatus, errorThrown){
						/*
						if(xhr.status == 1001) {
							alert(xhr.responseText);
						}
						*/
						
						switch(xhr.status) {
						case 1001:
							alert(xhr.responseText);
							break;
						}
					}
				});
			});
		}
		
		// 5. 회원 수정
		
		function fn_updateMember() {
			$('#update_btn').click(function(){
				var obj = {
					id: $('input:text[name="id"]').val(),
					name: $('input:text[name="name"]').val(),
					address: $('input:text[name="address"]').val(),
					gender: $('input:radio[name="gender"]:checked').val(),
					no: $('#view_area input:hidden[name="no"]').val()
				};
				$.ajax({
					url: 'updateMember.do',
					type: 'post',
					contentType: 'application/json',
					data: JSON.stringify(obj),
					success: function(resultMap) {
						if (resultMap.count > 0) {
							alert('회원 정보가 수정되었습니다.');
							fn_selectMemberList();
						}
					}
				});
			});
		}
		
		// 6. 회원 삭제
		function fn_deleteMember(){
			$('#delete_btn').click(function(){
				if(!confirm('삭제하시겠습니까?')){
					return false;					
				}
				var obj = {
						no: $('#view_area input:hidden[name="no"]').val()
				};
				$.ajax({
					url: 'deleteMember.do',
					type: 'post',
					contentType: 'application/json; charset=utf-8',
					data: JSON.stringify(obj),
					dataType: 'json',
					success: function(resultMap){
						if(resultMap.count > 0) {
							alert('삭제되었습니다.');
							fn_selectMemberList();
							$('input:text[name="id"]').val('').attr('readonly', false);
							$('input:text[name="name"]').val('');
							$('input:text[name="address"]').val('');
							$('input:radio[name="gender"]').prop('checked', false);			
						} else {
							alert('삭제되지않았습니다.');
						}
					}
				});
			});
		}
		
		// 7. 초기화
		function fn_init(){
			$('#init_btn').click(function(){
				$('input:text[name="id"]').val('').attr('readonly', false);
				$('input:text[name="name"]').val('');
				$('input:text[name="address"]').val('');
				$('input:radio[name="gender"]').prop('checked', false);				
			});
		}
		
	</script>	
	<style>
		#paging {
			width: 50%;
			margin: 0 auto;
			display: flex;
			justify-content: space-between;
			text-align: center;
		}
		#paging div {
			width: 40px;
			height: 20px;
		}
		.disable {
			color: silver;
		}
		.link {
			cursor: pointer;
		}
		.now_page {
			color: limegreen;
		}
	</style>
</head>
<body>
	
	<h1>회원 관리</h1>
	
	<div id="view_area">
		아이디<input type="text" name="id" id="id"><br>
		이름<input type="text" name="name" id="name"><br>
		주소<input type="text" name="address" id="address"><br>
		성별
		<input type="radio" name="gender" value="남" id="male"><label for="male">남</label>
		<input type="radio" name="gender" value="여" id="female"><label for="female">여</label><br>
		<input type="hidden" name="no">
		<input type="button" value="초기화" id="init_btn">
		<input type="button" value="등록하기" id="insert_btn">
		<input type="button" value="수정하기" id="update_btn">
		<input type="button" value="삭제하기" id="delete_btn">
	</div>
	<hr>
	
	<table border="1">
		<thead>
			<tr>
				<td>아이디</td>
				<td>이름</td>
				<td>주소</td>
				<td>성별</td>
				<td></td>
			</tr>
		</thead>
		<tbody id="member_list"></tbody>
		<tfoot>
			<tr>
				<td colspan="5">
					<div id="paging"></div>
				</td>
			</tr>
		</tfoot>
	</table>
	
</body>
</html>