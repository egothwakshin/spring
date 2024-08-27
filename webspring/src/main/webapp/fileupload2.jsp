<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여러개의 파일을 업로드하는 형태(Spring)</title>
</head>
<body>
<form id="frm" method="post" action="./fileupok2.do" enctype="multipart/form-data">
<p>첨부파일 업로드</p>
<!--
파일첨부1 : <input type="file" name="mfile"><br>
파일첨부2 : <input type="file" name="mfile"><br>
파일첨부3 : <input type="file" name="mfile"><br>
-->
파일첨부 : <input type="file" name="mfile" multiple="multiple">
<input type="button" value="전송" onclick="fileup()">
</form>
</body>
<script>
function fileup(){
	frm.submit();
}

</script>
</html>