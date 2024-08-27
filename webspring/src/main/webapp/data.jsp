<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring에 데이터 전송</title>
</head>
<body>
<!--
<form id="frm" method="post" action="./test.do">
-->
<form id="frm" method="get" action="./mainpage.do">
검색: <input type="text" name="search"><br>
<input type="button" value="전송" onclick="abc()">
</form>
</body>
<script>
function abc(){
	frm.submit();
}
</script>
</html>