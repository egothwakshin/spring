<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전화번호 인증 페이지(Spring-RequestMapping)</title>
</head>
<body>
<form id="frm" method="post" action="./telok.do">
연락처 : <input type="text" name="tel"><br><br>
이메일 : <input type="text" name="email">@<input type ="text" name="email"><br><br>
개인정보 동의함 : <input type="checkbox" name="agree" value="Y">
<input type="button" value="전송" onclick="telok()">
</form>
</body>
<script>
function telok(){
	frm.submit();
}
</script>
</html>