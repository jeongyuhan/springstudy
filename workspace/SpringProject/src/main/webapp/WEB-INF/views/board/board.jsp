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
			fn_selectBoardList(); // 전체목록 가져오기
			fn_paging(); // 페이징
			fn_init(); // 초기화
			fn_insertBoard(); // 작성
			fn_searchQuery() // 검색
			
			// 전체 댓글 불러오기
			fn_selectAllReply();
			// 게시글 댓글 작성
			fn_insert_reply();
		})
		
		// 전체 게시글
		var page = 1;
		function fn_selectBoardList(){
			var obj = {
					page : page
			};
			$.ajax({
				url: 'selectBoardList.do',
				type: 'post',
				contentType: 'application/json',
				data: JSON.stringify(obj),
				dataType: 'json',
				success: function(resultMap) {
					// 목록 생성
					$('#board_list').empty();
					if(resultMap.exists) {
						$.each(resultMap.list, function(i, board){
							$('<tr>')
							.append($('<td>').text(board.bno))
							.append($('<td>').html('<a href="selectBoardByNo.do?bno=' + board.bno + '">' + board.title + '</a>'))
							.append($('<td>').text(board.writer))
							.append($('<td>').text(board.hit))
							.append($('<td>').text(board.postdate))
							.append($('<td>').text(board.image))
							.appendTo('#board_list');
						});
					} else {
						$('<tr>')
						.append('<td colspan="6">등록된 게시글이 없습니다.</td>')
						.appendTo('#board_list');
					}
					var paging = resultMap.paging;
					$('#paging').empty();
					if(paging.beginPage <= paging.pagePerBlock) {
						$('<div>').addClass('disable').text('◀').appendTo('#paging');
					} else {
						$('<div>')
						.addClass('previous_block').addClass('link')
						.attr('data-page', paging.beginPage - 1)
						.text('◀')
						.appendTo('#paging');
					}
					for(let p = paging.beginPage; p <= paging.endPage; p++) {
						if(p == paging.page) {
							$('<div>')
							.addClass('now_page')
							.text(p)
							.appendTo('#paging')
						} else {
							$('<div>')
							.addClass('go_page').addClass('link')
							.attr('data-page', p)
							.text(p)
							.appendTo('#paging');
						}
					}
					if(paging.endPage == paging.totalPage) {
						$('<div>')
						.addClass('disable')
						.text('▶')
						.appendTo('#paging');
					} else {
						$('<div>')
						.addClass('next_block').addClass('link')
						.attr('data-page', paging.endPage + 1)
						.text('▶')
						.appendTo('#paging');
					}
				}								
			});
		}
		
		// 페이징 링크 처리
		function fn_paging() {
			$('body').on('click', '.previous_block', function(){
				page = $(this).attr('data-page');
				fn_selectBoardList();
			});
			$('body').on('click', '.go_page', function(){
				page = $(this).attr('data-page');
				fn_selectBoardList();
			});
			$('body').on('click', '.next_block', function(){
				page = $(this).attr('data-page');
				fn_selectBoardList();
			});
			
		}
		
		// 초기화 함수
		function fn_init(){
			$('#init_btn').click(function(){
				$('#column').val('');
				$('#query').val('');
				location.href = 'boardPage.do';
			});
		}
		
		// 게시글 작성하기
		function fn_insertBoard(){
			$('#insert_board_btn').click(function(){
				location.href = 'insertBoardPage.do';
			});
		}
		
			
		// 검색기능
		function fn_searchQuery(){
			$('#search_btn').click(function(){
				if($('#column').val() == '') {
					alert('카테고리를 선택해주세요.');
					return false;
				}
				if($('#query').val() == '') {
					alert('검색어를 입력해주세요.');
					$('#query').focus();
					return false;
				}
				var obj2 = {
					page : page,
					column: $('#column').val(),
					query: $('#query').val()
				};
				$.ajax({
					url: 'searchQuery.do',
					type: 'post',
					contentType: 'application/json',
					data: JSON.stringify(obj2),
					dataType: 'json',
					success: function(resultMap){
						console.log(resultMap.exists);
						// 목록 생성
						$('#board_list').empty();
						if(resultMap.exists) {
							$.each(resultMap.list, function(i, board){
								$('<tr>')
								.append($('<td>').text(board.bno))
								.append($('<td>').html('<a href="selectBoardByNo.do?bno=' + board.bno + '">' + board.title + '</a>'))
								.append($('<td>').text(board.writer))
								.append($('<td>').text(board.hit))
								.append($('<td>').text(board.postdate))
								.append($('<td>').text(board.image))
								.appendTo('#board_list');
							});
						} else {
							$('<tr>')
							.append('<td colspan="6">검색된 게시글이 없습니다.</td>')
							.appendTo('#board_list');
						}
						var paging = resultMap.paging;
						$('#paging').empty();
						if(paging.beginPage <= paging.pagePerBlock) {
							$('<div>').addClass('disable').text('◀').appendTo('#paging');
						} else {
							$('<div>')
							.addClass('previous_block').addClass('link')
							.attr('data-page', paging.beginPage - 1)
							.text('◀')
							.appendTo('#paging');
						}
						for(let p = paging.beginPage; p <= paging.endPage; p++) {
							if(p == paging.page) {
								$('<div>')
								.addClass('now_page')
								.text(p)
								.appendTo('#paging')
							} else {
								$('<div>')
								.addClass('go_page').addClass('link')
								.attr('data-page', p)
								.text(p)
								.appendTo('#paging');
							}
						}
						if(paging.endPage == paging.totalPage) {
							$('<div>')
							.addClass('disable')
							.text('▶')
							.appendTo('#paging');
						} else {
							$('<div>')
							.addClass('next_block').addClass('link')
							.attr('data-page', paging.endPage + 1)
							.text('▶')
							.appendTo('#paging');
						}
					}
				});
			});	
		}
		
		// 전체 댓글 불러오기
		var page = 1;
		function fn_selectAllReply() {
			var obj = {
					page : page
			}
			$.ajax({
				url: 'selectAllReply.do',
				type: 'post',
				contentType: 'application/json',
				data: JSON.stringify(obj),
				dataType: 'json',
				success: function(resultMap){
					$('#reply_list').empty();
					if(resultMap.exists) {
						$.each(resultMap.list, function(i, reply){
							$('<tr>')
							.append($('<td>').text(reply.replywriter))
							.append($('<td>').text(reply.replycontent))
							.append($('<td>').html(getDateFormat(reply.replypostdate)))
							.append($('<td>').text(reply.replyip))
							.appendTo('#reply_list');
						});
					} else {
						$('<tr>')
						.append('<td colspan="4">등록된 댓글이 없습니다.</td>')
						.appendTo('#reply_list');
					}
					var paging = resultMap.paging;
					$('#paging2').empty();
					if(paging.beginPage <= paging.pagePerBlock) {
						$('<div>').addClass('disable').text('◀').appendTo('#paging2');
					} else {
						$('<div>')
						.addClass('previous_block').addClass('link')
						.attr('data-page', paging.beginPage - 1)
						.text('◀')
						.appendTo('#paging2');
					}
					for(let p = paging.beginPage; p <= paging.endPage; p++) {
						if(p == paging.page) {
							$('<div>')
							.addClass('now_page')
							.text(p)
							.appendTo('#paging2')
						} else {
							$('<div>')
							.addClass('go_page').addClass('link')
							.attr('data-page', p)
							.text(p)
							.appendTo('#paging2');
						}
					}
					if(paging.endPage == paging.totalPage) {
						$('<div>')
						.addClass('disable')
						.text('▶')
						.appendTo('#paging2');
					} else {
						$('<div>')
						.addClass('next_block').addClass('link')
						.attr('data-page', paging.endPage + 1)
						.text('▶')
						.appendTo('#paging2');
					}
				}
			});
		}
		
		// SYSDATE -> 패턴으로 변환
		function getDateFormat(date) {
			var d = new Date(date);
			var year = d.getFullYear();
			var month = '' + (d.getMonth() + 1);
			var day = '' + d.getDate();
			if (month.length < 2) {
			    month = '0' + month;
			}
			if (day.length < 2) {
			     day = '0' + day;
			}
			return [year, month, day].join('-');
		}
		
		// 댓글 작성
		function fn_insert_reply() {
			$('#insert_reply_btn').click(function(){
				if($('#replywriter').val() == '') {
					alert('로그인이후에 이용가능합니다.');
					location.href = 'index.do';
					return false;
				}
				if($('#replycontent').val() == '') {
					alert('내용을 입력해주세요.');
					$('#replycontent').focus();
					return false;
				}
				
				$.ajax({
					url: 'insertReply.do',
					type: 'get',
					data: $('#f2').serialize(),
					dataType: 'json',
					success: function(resultMap) {
						$('#replycontent').val('');
						alert("댓글이 추가되었습니다.");
						fn_selectAllReply();
					}
				});
				
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
		#paging2 {
			width: 50%;
			margin: 0 auto;
			display: flex;
			justify-content: space-between;
			text-align: center;
		}
		#paging2 div {
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
		
		#f2 {
			display: flex;
		}
		
		#replywriter {
			width: 50px;
		}
		#replycontent {
			width: 300px;
		}
		#insert_reply_btn{
			width: 50px;
		}
		
	</style>
</head>
<body>
	
	<h1>갤러리 게시판</h1>
	
	<div class="search_box">
		<form id="f" method="get">
			<select id="column" name="column">
				<option value="">:::선택:::</option>
				<option value="TITLE">제목</option>
				<option value="WRITER">작성자</option>
			</select>
			<input type="text" id="query" name="query"> 
			<input type="button" value="검색" id="search_btn">
			<input type="button" value="초기화" id="init_btn">
		</form>
	</div>
	
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
			<tbody id="board_list"></tbody>
			<tfoot>
				<tr>
					<td colspan="6">
						<div id="paging"></div>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
	<c:if test="${loginUser == null}">
		<a href="index.do">로그인 하러가기</a>
	</c:if>
	<c:if test="${loginUser != null}">			
		<input type="button" value="게시글 작성하기" id="insert_board_btn">
	</c:if>
	
	<br><hr><br>
	
	<h2>갤러리 게시판 댓글</h2>
	
	<div class="reply_write_box">
		<form id="f2" method="get">		
			<input type="hidden" id="bno" name="bno" value="${boardDTO.bno}">			
			<input type="text" id="replywriter" name="replywriter" value="${loginUser.id}" placeholder="작성자" readonly><br>		
			<textarea id="replycontent" name="replycontent" rows="4" cols="22"  placeholder="댓글 작성"></textarea><br>		
			<input type="button" value="작성" id="insert_reply_btn">
		</form>
	</div>
	<div class="reply_list_box">
		<table border="1">
			<thead>
				<tr>
					<td>작성자</td>
					<td>내용</td>
					<td>작성일</td>
					<td>작성자IP</td>
				</tr>
			</thead>
			<tbody id="reply_list">
			</tbody>
			<tfoot>
				<tr>
					<td colspan="6">
						<div id="paging2"></div>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
	
</body>
</html>