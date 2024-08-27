<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cp" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String vidx = request.getParameter("vidx");
	String url = "";
	if(vidx==null){
		out.print("동영상을 시청할 메뉴를 클릭하세요.");
	}
	else{
		url = "http://localhost:8080/youtube/";	
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동영상 스트리밍 서비스</title>

</head>
<body>
<!-- 메뉴에 가상의 속성을 이용하여 attribute로 해당 값을 로드하여 GET으로 전송시킴 -->
<ul id="menu">
<li data="11">에이핑크 뮤직비디오1</li>
<li data="22">에이핑크 뮤직비디오2</li>
</ul>
<br><br><br><br>
<cp:set var="url" value="<%=url%>"/>
<cp:set var="vidx" value="<%=vidx%>"/>
<!-- 해당 메뉴에 맞는 값을 받아서 CDN API 서버를 통하여 해당 영상을 로드하는 역할 -->
<video width="320" height="240" autoplay="autoplay" muted="muted">
	<source src="${url}${vidx}" type="video/mp4">
</video>
</body>
<script type="module">
import {m_select} from "./youtube.js?v=1";
//해당 오브젝트를 클릭시 해당 자식 오브젝트에 있는 가상의 속성을 통하여 값을 class로 전달
document.querySelector("#menu").addEventListener("click",function(z){
	console.log(z.target.attributes[0].textContent);	//z만 콘솔찍고 보라색글씨 따라 들어감
	var data = z.target.attributes[0].textContent;
	new m_select().m_load(data);
});
</script>
</html>