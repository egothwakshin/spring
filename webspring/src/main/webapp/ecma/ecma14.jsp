<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajax 요청사항에 맞는 배열값을 받기</title>
</head>
<body>
<input type="button" value="데이터 요청" id="btn">
<table border="1">
	<thead>
	<tr>
		<td>번호</td>
		<td>쿠폰명</td>
		<td>할인율</td>
		<td>유효기간</td>
	</tr>
	</thead>
	<tbody id="dataview">
	
	</tbody>
</table>
</body>
<script type="module">
	import {data_load} from "./ecma14.js?v=1";
	document.querySelector("#btn").addEventListener("click",function(){
		new data_load().json_data();
	});
</script>
</html>