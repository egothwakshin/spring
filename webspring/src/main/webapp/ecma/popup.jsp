<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팝업 데이터</title>
</head>
<body>
<span onclick="datacall('java')">java L</span><br>
<span onclick="datacall('html')">html Y</span><br>
<span onclick="datacall('ecma')">ecma S</span><br>
</body>
<script>
function datacall(z){
	//window.opener.frm.data.value = z;
	//self.close();
	
	//자신을 호출한 부모페이지에 postMessage로 배열값으로 전달하는 형태
	//"*": 모두 사이트에서 해당 페이지를 로드할 수 있음
	//"http://naver.com" => naver.com 도메인만 접속이 가능함
	parent.window.postMessage({msg:z,"*"});
}

</script>
</html>