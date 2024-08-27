<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>갤러리 게시판(글쓰기)</title>
</head>
<body>
<form id="frm" method="post" action="./galleryok.do" enctype="multipart/form-data">
글쓴이: <input type="text" name="gwriter"><br>
제목: <input type="text" name="gsubject"><br>
내용: <textarea rows="30" cols="100" name="gtext"></textarea>
<!-- 첨부파일만 dao에서 사용하는 이름을 적용하지 않음 -->
첨부파일: <input type="file" name="files" multiple="multiple"><br>
<input type="button" value="등록" onclick="gallery()">
</form>
</body>
<script>
function gallery(){
	frm.submit();
}
</script>
</html>