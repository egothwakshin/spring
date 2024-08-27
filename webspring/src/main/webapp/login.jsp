<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring-로그인 (모든 메소드를 활용하는 페이지)</title>
</head>
<body>
<form id="frm">
아이디 : <input type="text" name="mid"><br>
패스워드 : <input type="text" name="mpass"><br>
<input type="button" value="전송" onclick="abc()">
</form>
</body>
<script>
function abc(){
	frm.method="post";
	frm.action="./mainpage4.do";
	frm.submit();
}
</script>
</html>