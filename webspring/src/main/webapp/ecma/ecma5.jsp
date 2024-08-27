<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ECMA Script Class로 핸들링 하기</title>
</head>
<body>
연락처: <input type="text" id="tel1" maxlength="3"> -
<input type="text" id="tel2" maxlength="4"> -
<input type="text" id="tel3" maxlength="4">
<input type="button" value="인증번호 받기" id="btn">
<br><br>
<form id="frm" method="post" action="./ecmaok.do">
<input type="text" name="mid"><br>
<input type="password" name="mpass"><br>
<input type="submit" value="로그인">
</form>
</body>
<script type="module">
import {tels,login} from "./ecma5.js";
/*
	submit 사용시 function에 인자값을 필수로 입력 후 preventDefault()를 이용하여
	action이 작동되지 않도록 설정함
*/
document.querySelector("#frm").addEventListener("submit",function(z){
	z.preventDefault();
	//console.log(this.mpass.value); 	//this => #frm 끌어오기때문에 var선언으로 name가져올필요 없음.
	new login(this.mid,this.mpass).login_check();
});

document.querySelector("#btn").addEventListener("click",function(){
	this.result = new tels();
	this.result.tel_check();
});
</script>
</html>