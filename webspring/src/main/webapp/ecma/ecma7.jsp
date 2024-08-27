<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cp" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- fmt: format-type -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>무비 정보 리스트 출력 - ECMA+JSTL</title>
</head>
<body>
<table border="1">
<tr>
<td>번호</td>
<td>영화제목</td>
<td>영화관</td>
<td>예매가격</td>
<td>상영일자</td>
</tr>
<cp:forEach var="m" items="${all}" varStatus="vs">
<tr>
<td>${count-vs.index}</td>
<td>${m.get(1)}</td>
<td>${m.get(2)}</td>
<td><fmt:formatNumber value="${m.get(3)}" pattern="#,###"/>원</td>
<td>${m.get(4)}</td>
</tr>
</cp:forEach>
</table>
</body>
</html>