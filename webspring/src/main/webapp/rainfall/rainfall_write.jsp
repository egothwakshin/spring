<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강우량 데이터 등록 페이지</title>
</head>
<body>
<form id="frm">
등록일자 : <input type="date" name="today" onchange="date_check(this.value)">
<font color="red" id="error_red" style="display:none;">※이미 등록된 일자입니다.</font>
<br>
서울 : <input type="text" name="area_part1" value="0" maxlength="3"><br>
경기도 : <input type="text" name="area_part2" value="0" maxlength="3"><br>
세종 : <input type="text" name="area_part3" value="0" maxlength="3"><br>
대전 : <input type="text" name="area_part4" value="0" maxlength="3"><br>
강원도 : <input type="text" name="area_part5" value="0" maxlength="3"><br>
<button type="button" id="btn">강수량 등록</button>
</form>
</body>
<script>
function date_check(day){
	if(day!=""){
		var ajax = new XMLHttpRequest();
		ajax.onreadystatechange = function(){
			if(ajax.readyState==4 && ajax.status==200){
				//console.log(this.response);
				if(this.response!=0){
					document.getElementById("error_red").style.display="block";
				}
				else{
					document.getElementById("error_red").style.display="none";
				}
			}
		}
		ajax.open("GET","./rainfall_check.do?today="+day,true);
		ajax.send();
	}
	else{	//이벤트 발생시 값을 못가져왔을 경우
		var dates = frm.today.value;
		if(dates!=""){
			date_check(dates);
		}
	}
	
}

	document.querySelector("#btn").addEventListener("click",function(){
		frm.method="POST";
		frm.action="./rainfall_writeok.do";
		frm.submit();
	});

</script>
</html>