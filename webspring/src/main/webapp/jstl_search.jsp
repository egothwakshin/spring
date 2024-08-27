<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%
	String search = request.getParameter("search");
	//out.print(search);
	String data[] = {"삼겹살","등심","안심","갈매기살","목살","안창살"};
%>
<cr:set var="search" value="<%=search%>"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL로 SPA검색기능 사용법</title>
</head>
<body>

<form id="frm" method="post" action="./jstl_search.jsp">
검색어: <input type="text" name="search" value="${search}">
<input type="button" value="검색" onclick="spa_form()">
</form>
<!--
해당메뉴와 동일한 메뉴가 있을 경우 해당 메뉴만 출력되어야 하며,
검색한 메뉴가 배열에 없을 경우 "해당 메뉴는 없는 데이터입니다" 라고 출력합니다.
 -->
<br>
<p>메뉴리스트</p>
<!-- indexOf를 이용하여 검색된 값과 배열에 적용된 단어 검토하여 출력하는 형태 -->
<cr:forEach var="ck" items="<%=data%>">
<cr:if test="${fn:indexOf(ck,search)!=-1}">
<%-- ${ck} --%>
</cr:if>
</cr:forEach>


<ul>
<cr:set var="check" value="N"/>
<cr:forEach var="menu" items="<%=data%>">
<cr:if test="${search==menu && search != ''}">
<li>${menu}</li>
<cr:set var="check" value="Y"/>
</cr:if>
<cr:if test="${search==''}">
<cr:set var="check" value="Y"/>
<li>${menu}</li>
</cr:if>
</cr:forEach>
<cr:if test="${check=='N'}">
<li>해당메뉴는 없는 데이터입니다.</li>
</cr:if>
</ul>
</body>
<script>
function spa_form(){
	frm.submit();
}
</script>
</html>