<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring Fileupload 기능사용법</title>
</head>
<body>
<form id="frm" method="post" action="./fileupok.do" enctype="multipart/form-data">
<p>첨부파일 업로드</p>
파일첨부 : <input type="file" name="mfile"><br>
<input type="button" value="전송" onclick="fileup()">
</form>
</body>
<script>
function fileup(){
	frm.submit();
}

</script>
</html>