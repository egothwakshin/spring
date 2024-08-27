<%@page import="java.util.Arrays"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>  
<%
	//jstl에 반복문 처리 => 배열형태로 출력 (원시배열, 클래스배열)
	//jstl에서는 권고사항 주로 클래스배열(ArrayList,LinkedList,Map)
	String data[] = new String[]{"hong","kim","park","kang","jang"};
	
	request.setAttribute("data", data);
	
	ArrayList<String> al = new ArrayList();
	al.add("테스트1");
	al.add("테스트2");
	al.add("테스트3");
	al.add("테스트4");
	al.add("테스트5");
	
	/*
	응용문제 해당 배열값에 맞는 jstl 출력코드를 작성하시오.
	2의 배수 및 5의 배수인 결과값만 출력시키시오.
	*/
	Integer number[] = {22,10,19,27,25,33,35,40,2,55,50};
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 문법사용3(반복문-foreach)</title>
</head>
<body>
	<!-- items: 모든 배열값을 처리할 때 사용하는 속성값 -->
	<!-- jsp변수를 items 속성에 받은후 var로 출력 -->
	<!-- begin(시작값), end(종료값) -->
	<ol>
	<cr:forEach var="z" items="<%=data%>" begin="1" end="3">
	<li>${z}</li>
	</cr:forEach>
	</ol>
	<!-- jsp에서 attribute로 생성된 이름을 items로 받은 후 출력 -->
	<ol>
	<cr:forEach var="z" items="${data}">
	<li>${z}</li>
	</cr:forEach>
	</ol>
	<ul>
	<cr:forEach var="z" items="<%=al%>">
	<li>${z}</li>
	</cr:forEach>
	</ul>
	<!-- data배열값중 park이라는 사용자만 출력되도록하시오 -->
	<cr:forEach var="z" items="${data}">
	<cr:if test="${z=='park'}"> <%--반복문 진행시 해당 조건에 맞을 경우 출력 --%>
	<div>${z}</div>
	</cr:if>
	</cr:forEach>
	<br><br>
	
	<cr:forEach var="z" items="<%=number%>">
	<cr:if test="${z % 2 == 0 || z % 5 == 0}">
	${z}
	</cr:if>	
	</cr:forEach>
	<br>
	<cr:forEach var="z" items="<%=number%>">
	<cr:if test="${z % 2 == 0 && z % 5 == 0 }">
	${z}
	</cr:if>
	</cr:forEach>
	
	
</body>
</html>