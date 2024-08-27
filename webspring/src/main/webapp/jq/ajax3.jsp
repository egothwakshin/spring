<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Jquery AJAX - 외부 서버 통신</title>
<script src="./jquery.js?v=1"></script>
<script>
	$(function(){
		$("#btn").click(function(){
			/*
			$.ajax({
				url: "http://172.30.1.31:8080/jq/rest_json3.do",
				cache: false,
				type: "GET",
				dataType: "html",
				success:function($data){
					console.log($data);
				},
				error:function(){
					console.log("외부서버 접속 오류!!");
				}
			});
			*/
			$.ajax({
				url: "http://172.30.1.31:8080/jq/rest_json3.do",
				cache: false,
				type: "GET",
				dataType: "JSON",
				success:function($data){
					console.log($data);
				},
				error:function(){
					console.log("외부서버 접속 오류!!");
				}
			});
			
		});
	});
</script>
</head>
<body>
<input type="button" value="외부 데이터 로드" id="btn">
</body>
</html>