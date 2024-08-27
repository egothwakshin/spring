<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- jstl엔진 -->
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>  
<!-- jstl 각종 함수 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<!-- jstl database 관련사항 -->
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %> 
<!-- jstl 단독으로 사용하는 경우 없음. do에서 호출받아서 사용 (Model, ModelandView) -->
<%
	String user = "홍길동";
	HttpSession hs = request.getSession();
	hs.setAttribute("ssdata", "15881004");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 문법사용1</title>
</head>
<body>
${ssdata}<br><br>
<!-- out태그는 out.print와 같은 형태의 출력태그입니다. -->
<cr:out value="값출력"/>
<br><br>
<!-- set태그는 setattribute와 동일한 형태의 출력태그입니다. -->
<cr:set var="a" value="에이핑크"/>${a}
<br><br>
<!-- jsp에 있는 값을 jstl로 변환시 set을 이용하여 변환 -->
<cr:set var="b" value="<%=user%>"/>${b}
<br><br>
<!-- scope="session" 을 활성화시 session 생성 또는 로드 활용가능 -->
<cr:set var="c" value="jstl문법" scope="session"/>${c}<br><br>

<!--
기존 session 삭제 또는 초기화하고싶을 경우 다음과같이 사용가능
세션초기화시 var속성에 동일한 세션명을 입력하면 된다.
 -->
세션초기화 전 데이터: ${ssdata}<br>
<cr:set var="ssdata" value="" scope="session"/>
세션초기화 후 데이터: ${ssdata}<br>

<!-- scope: request는 value값을 var변수에 값을 저장하는 형태 -->
<cr:set var="zz" value="aaa" scope="request"/>
${zz}<br><br>
<%
String aname="테스트입니다";
%>
<cr:set var="kk" scope="request"><%=aname%></cr:set>
${kk}<br><br>

</body>
</html>