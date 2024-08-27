<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!-- DB연결 전용태그 -->
<sql:setDataSource var="db" driver="com.mysql.cj.jdbc.Driver"
url="jdbc:mysql://localhost:3306/cms"
user="hana"
password="hana1234"/>
<!-- query(ddl) 사용하는 태그 -->
<%-- 
<sql:query var="ps" sql="select * from review" dataSource="${db}"/>
--%>

<!-- query(ddl) 사용법2 -->
<cr:set var="table" value="review"/>
<cr:set var="order" value="order by ridx desc"/>
<sql:query var="ps" dataSource="${db}">
select * from ${table} ${order}
</sql:query>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL로 Database 연결하기</title>
</head>
<body>
<table border="1">
<tr>
<td>제목</td>
<td>글쓴이</td>
<td>날짜</td>
</tr>
<!-- rows를 이용하여 table의 컬럼명으로 출력할 수 있음 -->
<cr:forEach var="row" items="${ps.rows}">
<cr:set var="subject" value="${fn:length(row['rtitle'])}"/>
<tr>
<!-- 게시판 제목의 범위 글자수 이상 되었을 경우 말줄임표가 나오는 코드 -->
<cr:if test="${subject>10}">
<cr:set var="jum" value="..."/>
</cr:if>
<td>${fn:substring(row['rtitle'],0,10)} ${jum}</td>
<td>${row['rname']}</td>
<td>${fn:substring(row['rdate'],0,10)}</td>
</tr>
</cr:forEach>
</table>
</body>
</html>