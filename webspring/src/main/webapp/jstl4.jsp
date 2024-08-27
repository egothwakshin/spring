<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>  
<%
	ArrayList<String> al = new ArrayList<String>();
	al.add("홍길동1");
	al.add("홍길동2");
	al.add("홍길동3");
	al.add("홍길동4");
	al.add("홍길동5");
	
	ArrayList<ArrayList<String>> al2 = new ArrayList<ArrayList<String>>();
	ArrayList<String> data = new ArrayList<String>();
	data.add("홍범기");
	data.add("최연욱");
	data.add("김나린");
	data.add("이윤석");
	data.add("신희문");
	data.add("김선숙");
	al2.add(data);
	ArrayList<String> data2 = new ArrayList<String>();
	data2.add("A");
	data2.add("B");
	data2.add("O");
	data2.add("A");
	data2.add("AB");
	data2.add("O");
	al2.add(data2);
	out.print(al2+"<br>");
	
	String members[][] = {
				{"김민지","강휘","김지현","김하주"},
				{"A","B","A","B"}	
				};
	
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 문법사용3(반복문-foreach)</title>
</head>
<body>
<!--
varStatus: 배열번호, 순서, 갯수.... 속성값
index: 배열노드번호
first: true(첫번째 데이터), false(그 외 데이터)
last: true(마지막 데이터), false(그 외 데이터)
count: 데이터 +1씩 카운팅
-->
<cr:forEach var="z" items="<%=al%>" varStatus="n">
데이터 기준: ${n.first}
번호: ${n.index}
데이터값: ${z}
총 데이터 개수 카운팅: ${n.count}
<br>
</cr:forEach>
<br>
<!-- 2차배열~다차원배열 -->
<cr:forEach var="a" items="<%=al2%>"> <!-- 2바퀴 -->
<cr:forEach var="b" items="${a}">	<!-- 6바퀴 -->
${b}<br>
</cr:forEach>
</cr:forEach>
<br>
<!-- 각그룹배열의 첫번째 값만 출력하는 형태 -->
<cr:forEach var="aa" items="<%=al2%>"> <!-- 2바퀴 -->
${aa.get(0)}<br> <!-- get(배열노드번호) -->
</cr:forEach>
<br>
<!-- 홍범기: A형... 2차배열에 데이터를 출력하는 방식 -->
<cr:set var="bb" value="<%=al2.get(1)%>"/>
<cr:forEach var="aa" items="<%=al2.get(0)%>" varStatus="no"> <!-- 6바퀴 -->
고객명: ${aa} / 혈액형: ${bb.get(no.index)}형 <br>
</cr:forEach>
<br>
<!-- 원시2차배열 출력방식 -->
<cr:set var="dd" value="<%=members[1]%>"/>
<cr:forEach var="cc" items="<%=members[0]%>" varStatus="no">
고객명: ${cc} / 혈액형: ${dd[no.index]}형<br>
</cr:forEach>
</body>
</html>