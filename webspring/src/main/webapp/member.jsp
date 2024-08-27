<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="aaa" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 JSTL + Spring + DataSource</title>
</head>
<body>
<form id="frm" method="post" action="./memberok.do">
아이디 : <input type="text" name="uid"><br>
패스워드 : <input type="password" name="upass"><br>
고객명 : <input type="text" name="uname"><br>
<input type="button" value="간편회원가입" onclick="join()">
</form>
</body>
<script>
function join(){
	frm.submit();
}
</script>
</html>