<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajax 동기화 및 비동기화 차이점</title>
<style>
.box{
width:1px;
height:30px;
border:1px solid black;
transition:all 5s ease;
background-color: blue;
}

</style>
</head>
<body>
<div class="box" id="box"></div>
</body>
<script>
document.getElementById("box").setAttribute("style","width:0px");

function datacall(){
	setTimeout(() => {
		ani();
	}, 100);
	var http, result;
	http = new XMLHttpRequest();
	http.onreadystatechange = function(){
		if(http.readyState==4 && http.status==200){	//backend 5초 loading...
			document.getElementById("box").style.display = "none";
			console.log(this.response);
		}
	}
	//false: 동기화. 단, 동기화 작업시 Backend와 시간간격을 체크하여 작업을 선행해야함 (페이지 자체가 로딩걸림)
	//true: 비동기화. 결과값에 대한 통신시간과 관계없이 해당페이지를 출력시켜버림
	http.open("POST","./ajaxok.do",true); //true:비동기화	false:동기화
	http.setRequestHeader("content-type","application/x-www-form-urlencoded");
	http.send("mid=hong");
	console.log("내가 먼저 출력됨!!");
}

function ani(){
	document.getElementById("box").style.width = "300px";
}
datacall();

</script>
</html>