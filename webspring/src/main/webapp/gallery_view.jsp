<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cp" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>갤러리 게시판(상세 내용보기)</title>
</head>
<body>
글쓴이: ${one_list.get(1)}<br>
제목: ${one_list.get(2)}<br>
내용: ${one_list.get(3)}<br>
<!-- 사용자가 업로드한 파일명도 유지하면서 개발자가 변경된 파일명으로 출력으로 할 경우 forEach에 items 사용하기 어려움 -->
<!-- class배열 fn:split을 사용할 경우 원시배열[] 로 출력 -->
<cp:set var="imgs" value="${fn:split(one_list.get(4),',')}"></cp:set>
<cp:set var="imgs2" value="${fn:split(one_list.get(5),',')}"></cp:set>
<cp:set var="ea" value="${fn:length(imgs)}"></cp:set>
<cp:forEach var="no" begin="0" end="${ea-1}">
첨부파일:<a href="./upload/${imgs2[no]}" target="_blank">${imgs[no]}</a><br>
</cp:forEach>
<br><br>
<input type="button" value="게시물 삭제" onclick="del_gallery()">
</body>
<script>
//javascript 안에 jstl, jsp 변수를 모두 받을 수 있음
var gidx="${one_list.get(0)}";
function del_gallery(){
	if(confirm("해당 게시물 삭제시 복구되지 않습니다. 삭제를 진행하시겠습니까?")){
		location.href='./gallery_del.do?gidx='+gidx;
	}
	
} 
</script>
</html>