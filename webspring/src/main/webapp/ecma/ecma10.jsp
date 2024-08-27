<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>외부 페이지 팝업 결과값 CORS 해결법</title>
</head>
<body>
<form id="frm">
값: <input type="text" name="data" id="data" readonly="readonly">
<input type="button" value="데이터 로드" id="btn">
</form>
</body>
<script type="module">
document.querySelector("#btn").addEventListener("click",function(){
	window.open("http://172.30.1.20:8080/ecma/popup.jsp","","width=400 height=400");
});
</script>
</html>