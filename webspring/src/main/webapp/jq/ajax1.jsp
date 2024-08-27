<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
아키텍처(데이터의 구조형태), 프로토콜(서버와 서버간의 통신)
 -->
<!-- 
API서버
-REST (GET,POST) - XML, JSON
-RESTful (GET,POST,PUT,DELETE) -> URI 파라미터
-CDN(GET,POST) - Image, Video
-SOAP - REST와 특성이 비슷함 (프로토콜) XML만 사용
-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Jquery Ajax & API 서버통신 (JSON)</title>
<script src="./jquery.js?v=1"></script>
<script>
	$(function(){
		$("#btn").click(function(){
			//$.get(), $.post()	
			
			//GET
			$.ajax({
				url : "./rest_json.do",
				cache : false,
				type : "GET",
				dataType : "html", 	//HTML, TXT, XML, JSON
				success : function($data){
					console.log(JSON.parse($data));
				},
				error : function($data){
					console.log($data);
				}				
			});
		});
			
		
		//JSON으로 핸들링하여 가져올 경우 JSON.parse를 사용하지 않음
		$("#btn2").click(function(){
			$.ajax({
				url : "../upload/test2.json", //json파일로 배열값을 로드하는 형태
				cache : false,
				type : "GET",
				dataType : "JSON", 	//HTML, TXT, XML, JSON
				success : function($data){
					//console.log(JSON.parse($data));
					//console.log($data);
					$("#htmlview").html("");	//html 내용 출력 초기화
					$.each($data["member"],function($node,$value){
						$("#htmlview").append("<li>"+$value+"</li>");
					});
				},
				error : function($data){
					console.log($data);
				}			
			});			
		});
		
		$("#btn3").click(function(){
	
			$.ajax({
				url : "./rest_json2.do",
				cache : false,
				type : "GET",
				dataType : "JSON", 	//HTML, TXT, XML, JSON
				success : function($data){
					console.log($data);
				},
				error : function($data){
					console.log($data);
				}				
			});
		});
		
		
		
	});

</script>
</head>
<body>
<input type="button" value="데이터 로드" id="btn">
<input type="button" value="JSON 로드" id="btn2">
<input type="button" value="URL JSON로드" id="btn3">
<ul id="htmlview">

</ul>
</body>
</html>