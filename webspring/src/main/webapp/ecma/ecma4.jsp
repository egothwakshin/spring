<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ECMA Script (핸들링 사용법) - es7 사용법</title>
</head>
<body>
<input type="text" name="mid" id="mid"><br>
<input type="button" value="중복체크" id="btn">
<br><br><br>
<!-- 
	두 개의 숫자를 입력받습니다. 해당 입력값 범위안에 있는 모든 값을 더하여
	결과를 출력하는 ES코드를 작성하시오.
	[예시]
	5, 8 => 5+6+7+8 => 출력
 -->
첫번째 값: <input type="text" id="no1"><br>
두번째 값: <input type="text" id="no2"><br>
<input type="button" value="결과출력" id="btn2">
</body>
<script type="module">
import {idcheck,allsum} from "./ecma4.js";
//export에 해당 함수의 인자값을 적용할 경우는 function을 선언 후 데이터값을 인자로 태우고 보내야함
document.querySelector("#btn").addEventListener("click",function(){
	var ob = document.getElementById("mid").value;
	idcheck(ob);
});

document.querySelector("#btn2").addEventListener("click",function(){
	var a = document.getElementById("no1").value;
	var b = document.getElementById("no2").value;
	allsum(a,b);
});
</script>
</html>