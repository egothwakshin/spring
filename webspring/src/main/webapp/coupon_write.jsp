<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠폰 생성 프로세서</title>
</head>
<body>
<form id="frm" method="post" action="./coupon_writeok.do">
쿠폰명: <input type="text" name="cpname"><br>
쿠폰할인율: <input type="text" name="cprate" value="30"><br>
쿠폰활성화: <input type="radio" name="cpuse" value="Y" checked>사용함
<input type="radio" name="cpuse" value="N">사용안함<br>
쿠폰만료일: <input type="date" name="cpdate"><br>
<input type="button" value="쿠폰 생성" onclick="cpwrite()"> 
</form>
</body>
<script>
function cpwrite(){
	frm.submit();
}
</script>
</html>