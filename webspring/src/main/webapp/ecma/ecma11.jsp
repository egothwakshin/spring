<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>배열로 Ajax(fetch)로 전송</title>
</head>
<body>

<input type="button" value="전송" id="btn">
</body>
<script type="module">
import {ecma_ajax} from "./ecma11.js?v=1";
document.querySelector("#btn").addEventListener("click",function(){
	//new ecma_ajax().arrs();
	new ecma_ajax().ajax_arr();
});
</script>
</html>