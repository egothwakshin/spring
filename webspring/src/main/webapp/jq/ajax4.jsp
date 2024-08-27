<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Javascript - Ajax</title>
</head>
<body>
<input type="button" value="데이터 로드" onclick="ajax()">
</body>
<script>
var html = new XMLHttpRequest();

function ajax(){
	html.onreadystatechange = function(){
		if(html.readyState==4 && html.status==200){
			console.log(JSON.parse(this.response));
		}
	}	
	//html.open("GET","./rest_ajax4.do",true);
	var keycode = "keycode=a1234";	//보안키가 다를경우 데이터를 받지 못함
	html.open("GET","http://172.30.1.51:8080/jq/rest_ajax4.do?"+keycode,true);
	html.send();	
}
</script>
</html>