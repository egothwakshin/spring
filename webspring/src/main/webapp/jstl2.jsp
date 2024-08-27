<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 문법사용2(if문)</title>
</head>
<body>
<!-- if문 선언 및 test가 조건식을 사용 var: true,false 결과형태-->
<cr:if test="${5 < 10}" var="result">
10이 큽니다.
</cr:if>
${result}
<cr:if test="${5 > 10}" var="result">
5가 큽니다.
</cr:if>
${result}
<br><br>
<!-- set과 함께 if문을 이용하여 출력하는 jstl 조건식 -->
<cr:set var="no" value="102"/>
<cr:if test="${no<100}">
해당 숫자는 100이하의 숫자입니다.
</cr:if>
<cr:if test="${no>100}">
해당 숫자는 100이상의 숫자입니다.
</cr:if>
<cr:if test="${no==100}">
해당 숫자는 100입니다.
</cr:if>
<br><br>
<!--  choose문을 활용한 when 조건식 -->
<cr:set var="no2" value="N"/>
 <!--
 조건식의 그룹 태그: choose
 ※choose 안에서는 html 주석 사용 불가능 -->
<cr:choose>
	<cr:when test="${no2=='Y'}"> <%-- 조건문1 --%>
		약관에 동의하셨습니다.
	</cr:when>
	<cr:when test="${no2 eq'N'}"> <%-- 조건문2. eq(==) --%>
		약관에 미동의하셨습니다.
	</cr:when>
	<cr:when test="${no2 ne 'Y'|| no2 ne 'N'}"> <%-- 조건문3. ne(!=), ||(or), &&(and)--%>
		약관 동의없이 페이지 이동
	</cr:when>
	<cr:otherwise>	<%-- 해당 조건 외에 실행되는 태그문법 --%>
		해당 값은 인식하지 못하였습니다.
	</cr:otherwise>
</cr:choose>
</body>
</html>