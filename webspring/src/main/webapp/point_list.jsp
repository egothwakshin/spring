<%@page import="api.pointdao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>포인트 생성 내역 리스트</title>
</head>
<body>
<p>데이터 리스트 출력</p>
<cr:forEach var="listdata" items="${all}">
<p>아이디: ${listdata.uid} </p>
<span>고유값: ${listdata.uidx}</span>
<span>고객명: ${listdata.uname} </span>
<span>포인트금액: ${listdata.upoint} </span>
<span>지급일자: ${listdata.udate} </span>
<input type="button" value="데이터 삭제" class="btn" data="${listdata.uidx}">
</cr:forEach>
</body>
<script>
var ea = document.querySelectorAll(".btn");
console.log(ea);
var w = 0;
while(w<ea.length){
	ea[w].addEventListener("click",function(){
		var data = this.getAttribute("data");
		location.href='/point_delete.do?uidx='+data;
	});
	w++;
}
</script>
</html>