<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cp" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- servlet을 사용하기 위해서는 functions 엔진을 로드해야 함 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>갤러리 게시판 리스트</title>
</head>
<body>
<form id="frm">
<table border="1" cellpaddiong="0" cellspaciong="0">
<thead>
	<tr>
		<th width="150">썸네일</th>
		<th width="450">제목</th>
		<th width="100">글쓴이</th>
		<th width="100">등록일</th>
	</tr>
</thead>
<tbody align="center">
<!-- JSTL if,else -->
<cp:forEach var="gdata" items="${all_list}">
	<tr>
	<!-- 
	null을 사용한 이유는 Database에 가져오는 값이 null로 입력되어있음
	컬럼에 null이 아닌 비어있는 값일 경우 ''로 표기함
	 -->
	<cp:if test="${gdata.get(4)==null}">	
		<td>
		NO IMAGE
		</td>
	</cp:if>
	<!-- 
	fn : contains, split, startWith, join, length, indexOf 
	split(데이터값,'분리할문자')=>원시배열로 구성됨 []
	-->
	<cp:if test="${gdata.get(4)!=null}">	
	<cp:set var="imgs" value="${fn:split(gdata.get(4),',')}"></cp:set>	
		<td>
		<img src="./upload/${imgs[0]}" width="100" height="100">
		</td>
	</cp:if>	
		<td align="left" onclick="gallery_view('${gdata.get(0)}')">${gdata.get(2)}</td>
		<td>${gdata.get(1)}</td>
		<td>${gdata.get(3).substring(0,10)}</td>
	</tr>
</cp:forEach>
</tbody>
</table>
</form>	
</body>
<script>
function gallery_view(no){
	location.href="./gallery_view.do?gidx="+no;
}

</script>
</html>