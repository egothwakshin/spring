<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>원격지 서버에 배열 데이터 전송(POST)</title>
</head>
<body>
<form id="frm">
<p>[] 형태의 배열 데이터 생성 - array</p>
배열값 생성: <input type="text" name="adata">
<input type="button" value="배열추가" id="btn">
<input type="button" value="ajax 전송" id="btn2">
<br><br><br>
<p>{} 형태의 배열 데이터 생성 - formdata</p>
배열키 이름: <input type="text" name="bdata"><br>
배열값: <input type="text" name="cdata"><br>
<input type="button" value="배열추가" id="btn3">
<input type="button" value="ajax 전송" id="btn4">
</form>
</body>
<script type="module">
import {test_ajax} from "./ecma12.js?v=2";

//array배열 생성버튼
document.querySelector("#btn").addEventListener("click",function(){
	if(frm.adata.value!=""){
		new test_ajax().array_make(frm.adata.value);
	}
	else{
		alert("배열 데이터값을 입력하셔야 합니다.");
	}
});

//ajax전송
document.querySelector("#btn2").addEventListener("click",function(){
	new test_ajax().ajax_post();
});

//form배열 생성
document.querySelector("#btn3").addEventListener("click",function(){
	/*
	if(frm.bdata.value==""||frm.cdata.value==""){
		alert("배열키 이름과 배열값을 모두 입력하셔야 합니다.");
	}
	else{
		new test_ajax().formdata_make(frm.bdata.value,frm.cdata.value);
	}
	*/
});
//ajax전송
document.querySelector("#btn4").addEventListener("click",function(){
	new test_ajax().ajax_post2();
});
</script>
</html>