<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSTL(core 필수) : JSTL 엔진 , JSTL(functions 유/무) : JSTL 함수모음 -->
<%@ taglib prefix="abc" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<!-- 
JSTL 변수 => JSP 변수로 이관 불가능
JSP 변수 => JSTL 로 이관 가능 (Javascript, ECMA, React ...)

1. do에서 ArrayList로 배열을 이관했을 경우 forEach 써서 값 전달 - 추천
2. do에서 ArrayList로 배열을 이관했을 경우 JSP로 ArrayList를 받은 후 JSTL에서 set 이용하여 값 전달
 -->
<%	//JSP 문법
	String data[] = {"a1","a2","a3"};
	request.setAttribute("data_arr", data);
	int ea = data.length;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 사용법</title>
</head>
<body>
<!-- set : setattribute를 이용하여 JSP에 있는 변수를 JSTL의 변수값으로 변환 -->
<abc:set var="jstl_ea" value="<%=ea%>"></abc:set>
데이터의 총 개수 ${jstl_ea}
<br><br>
<ol>
<!-- 
배열출력시 forEach 기본은 int활용, String (X) => 배열출력시 begin, end 사용 안함
String배열을 사용하고싶을 경우 클래스 배열을 활용하여 출력 또는 setAttribute를 이용하여 출력

배열데이터 전용 속성 : items
-->

<!-- JSP에 있는 변수를 JSTL items로 담아서 처리-->
<%--	//JSTL 주석사용법
<abc:forEach var="arr" items="<%=data%>">
--%>

<!-- JSP에 setattribute를 이용하여 JSTL로 items에 값을 담아서 처리 -->
<abc:forEach var="arr" items="${data_arr}">
<li>${arr}</li>
</abc:forEach>
</ol>

<!-- JSTL : forEach를 이용항 반복문을 사용함 (var:값,	begin:시작값, end:종료값) -->
<abc:forEach var="no" begin="1" end="5">
${no}
</abc:forEach>

<!-- while문으로 출력 (막코드) -->
<ul>
<%
int w = 0;
while(w<ea){
%>
<abc:set var="list" value="<%=data[w]%>"></abc:set>
<li>${list}</li>
<%
w++;
}
%>
</ul>



</body>
</html>