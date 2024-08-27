<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%
	String data= "박성현,박세은,서은진,임성민,최하영,최한결";

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL Split을 이용하여 출력하기</title>
</head>
<body>
<!-- 
문자열을 split을 이용하여 해당 값을 원사배열로 변화 후 해당값을
forEach를 이용하여 출력하는 코드형태
 -->
<cr:set var="name" value="<%=data%>"/>
<cr:set var="user" value="${fn:split(name,',')}"/>
<cr:forEach var="z" items="${user}">
${z} <br>
</cr:forEach>
</body>
</html>