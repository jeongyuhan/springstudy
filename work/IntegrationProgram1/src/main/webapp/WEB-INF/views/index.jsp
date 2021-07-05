<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
		$(document).ready(function(){
			fn_selectAll();
			fn_selectQuery();
			fn_init();
		})
		
		
		function fn_selectAll(){
			$.ajax({
				url: 'selectAll.do',
				type: 'get',
				dataType: 'json',
				success: function(resultMap){
					alert(resultMap.message);
					fn_listTable(resultMap.status, resultMap.list);
				}
			});
		}
		
		function fn_selectQuery(){
			$('#search_btn').click(function(){
				if($('#column').val() == '') {
					alert('검색 카테고리를 선택하세요.');
					$('#column').focus();
					return false;
				}
				$.ajax({
					url: 'selectQuery.do',
					type: 'get',
					data: $('#f').serialize(),
					dataType: 'json',
					success: function(resultMap){
						alert(resultMap.message);
						fn_listTable(resultMap.status, resultMap.list);
					}
				});
			});
		}
		
		
		
		
		
		function fn_listTable(status, list) {
			var date = new Date();
			const formatDate = (${resultMap.list.regdate})=>{
			let formatted_date = date.getDate() + "-" + (date.getMonth() + 1) + "-" + date.getFullYear()
			 return formatted_date;
			}
			$('#list').empty();
			if(status == 200) {
				$.each(list, function(i, board) {
					$('<tr>')
					.append($('<td>').text(board.title))
					.append($('<td>').text(board.content))
					.append($('<td>').text(formatDate(${resultMap.list.regdate})))
					.appendTo('#list');
				});
			} else if(status == 500) {
				$('<tr>')
				.append($('<td colspan="3">').text('없음'))
				.appendTo('#list');
			}
		}

		function fn_init(){
			$('#init_btn').click(function(){
				$('#column').val('');
				$('#query').val('');
				fn_selectAll();
			});
		}
	</script>
	<style>
		
		table {
			text-align: center;
		}
		
		td:nth-of-type(1) {
			width: 100px;
			height: 50px;
		}
		td:nth-of-type(2) {
			width: 900px;
			height: 50px;
		}
		td:nth-of-type(3) {
			width: 100px;
			height: 50px;
		}
	</style>
</head>
<body>
	
	<form id="f" method="get">
		<select id="column" name="column">
			<option value="">:::선택:::</option>
			<option value="title">제목</option>
			<option value="content">내용</option>
		</select>
		<input type="text" id="query" name="query">
		<input type="button" value="검색" id="search_btn">
		<input type="button" value="초기화" id="init_btn">
	</form>
	
	<br><br><hr><br><br>
	
	<table border="1">
		<thead>
			<tr>
				<th>제목</th>
				<th>내용</th>
				<th>작성일</th>
			</tr>
		</thead>
		<tbody id="list">
		
		</tbody>
	</table>
	
</body>
</html>