<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Jquery AJAX - XML</title>
<script src="./jquery.js?v=1"></script>
<script>
	$(function(){
		$("#btn").click(function(){
			$.ajax({
				url : "./data.xml",
				cache : false,
				type : "GET",
				dataType : "xml",
				success: function($data){
					//XML에만 있는 find: 태그를 지칭하여 해당 태그 안에 있는 text() 데이터 출력
					$($data).find("apink").each(function(){
						var $usernm = $(this).find("username").text();
						var $userid = $(this).find("userid").text();
						console.log($usernm);
						console.log($userid);
					});
				},
				error: function(){
					console.log("서버파일 오류!!");
				}
				
			});	
		});
	});
</script>
</head>
<body>
<input type="button" value="데이터 로드(XML)" id="btn">
</body>
</html>