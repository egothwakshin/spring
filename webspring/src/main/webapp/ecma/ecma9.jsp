<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ECMA Ajax POST 통신</title>
</head>
<body>
<form id="frm">
아이디: <input type="text" name="mid" id="mid">
<input type="button" value="중복확인" id="btn">
</form>
</body>
<script type="module">
import {logins} from "./ecma9.js?v=1";
document.querySelector("#btn").addEventListener("click",function(){
	if(frm.mid.value==""){
		alert("아이디를 입력하셔야 합니다.");
	}
	else{
		new logins().ajax_idck(frm.mid.value);
	}
});

</script>
</html>