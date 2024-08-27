<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC형태의 template를 이용한 리스트 출력결과</title>
</head>
<body>
<ul>
<cr:forEach var="lists" items="${result}">
<li>${lists.uid}</li>
</cr:forEach>
</ul>
</body>
</html>