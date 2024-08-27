<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강우량 데이터 수정 페이지</title>
</head>
<body>
<form id="frm">
<input type="hidden" name="ridx" value="${data.ridx}">
등록일자 : ${data.today} <br>
서울 : <input type="text" name="area_part1" value="${data.area_part1}" maxlength="3"><br>
경기도 : <input type="text" name="area_part2" value="${data.area_part2}" maxlength="3"><br>
세종 : <input type="text" name="area_part3" value="${data.area_part3}" maxlength="3"><br>
대전 : <input type="text" name="area_part4" value="${data.area_part4}" maxlength="3"><br>
강원도 : <input type="text" name="area_part5" value="${data.area_part5}" maxlength="3"><br>
<button type="button" id="btn">강수량 수정</button>
</form>
</body>
<script>
document.querySelector("#btn").addEventListener("click",function(){
	frm.method = "post";
	frm.action = "./rainfall_updateok.do";
	frm.submit();
});
</script>
</html>