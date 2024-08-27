<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ECMA Script (함수 사용법) - es7 사용법</title>
</head>
<body>
<input type="button" value="클릭" id="btn">
</body>
<!-- type="module"을 이용하여 js파일 로드함 -->
<script type="module">
/* import를 이용하여 외부 js파일을 로드 */
import a from "./ecma3.js";	

/* {} 호출받을 함수 */
import {abc,bbb} from "./ecma3.js";
abc("홍길동");
//이벤트를 발생시키는 핸들링 호출
document.querySelector("#btn").addEventListener("click",bbb);
</script>
</html>