<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ECMA로 데이터 등록 (영화정보) - 응용편 </title>
</head>
<body>
<form id="frm" method="post" action="./ecma6ok.do">
영화제목: <input type="text" name="msubject"><br>
영화관: <select name="cinema">
<option value="">[영화관 선택]</option>
<option value="CGV">CGV</option>
<option value="MEGA BOX">메가박스</option>
<option value="LOTTE">롯데시네마</option>
</select>
<br>
예매가격: <input type="text" name="ticketing" maxlength="5"><br>
최초 상영일자: <input type="date" name="screen_date"><br>
<input type="button" value="상영관 등록" id="btn">
</form>
</body>
<script type="module">
import {movieck} from "./ecma6.js";
document.querySelector("#btn").addEventListener("click",function(){
	new movieck().input_check();
});
</script>
</html>