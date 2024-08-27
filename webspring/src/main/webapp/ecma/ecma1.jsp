<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ECMA Script (함수 사용법) - class</title>
<!-- 
ECMA Script => jquery 사용형태 + Javascript 사용형태
es5~6 (IE) => es7~15
 -->
</head>
<body>
<input type="button" value="클릭" id="btn">
</body>
<script>
//onclick 핸들링
document.querySelector("#btn").addEventListener("click",function(){
	z.ccc();			//let z = new abc();
	new zzz().c(1004);
});
//선언식 class 형태
var zzz = class bbb{
	c(val){
		console.log("연습입니다.");
		console.log(val);
	}
}
console.log(zzz.name);	//해당 변수의 class명을 띄워주는 명령어

class abc{	//class 함수를 사용하여 모든 메소드를 컨트롤하는 형태 구조
	aaa(){
		console.log("함수1")
	}	
	bbb(data){
		console.log("데이터: " + data);
	}
	ccc(){
		alert("테스트 진행!!");
	}
}

let z = new abc();
z.aaa();
z.bbb("홍길동");

</script>
</html>