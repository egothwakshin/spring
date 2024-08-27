<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ECMA - ajax로 데이터 로드</title>
</head>
<body>
<input type="button" value="ECMA AJAX" id="btn">
</body>
<script type="module">
import {data} from "./ajax5.js";
document.querySelector("#btn").addEventListener("click",function(){
	data.ajax();
});

</script>
</html>