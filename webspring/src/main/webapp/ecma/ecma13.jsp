<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ECMA - Ajax(Delete)</title>
</head>
<body>
<input type="button" value="데이터 삭제 요청" id="btn"><br><br>
<input type="button" value="외부서버 데이터 삭제 요청" id="btn2">
</body>
<script type="module">
import {data_call,host_call} from "./ecma13.js?v=1";
document.querySelector("#btn").addEventListener("click",function(){
	new data_call().delete_call();
});

document.querySelector("#btn2").addEventListener("click",function(){
	new host_call().delete_call();
});
</script>
</html>