<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>포인트 사용자에게 지급</title>
</head>
<body>
<form id="frm" method="post" action="./pointok.do">
사용자 아이디: <input type="text" name="uid"><br>
사용자 이름: <input type="text" name="uname"><br>
지급할 포인트: <input type="text" name="upoint" maxlength="5">
<input type="button" value="포인트 지급" id="btn">
</form>
</body>
<script>
document.querySelector("#btn").addEventListener("click",function(){
	frm.submit();
});

</script>
</html>