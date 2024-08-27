<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ECMA Script (함수 사용법) - class상속</title>
</head>
<body>

</body>
<script>
class box{
	abc(data){
		//가상변수
		this.msg = data + "님 환영합니다.";
		return this.msg;
	}
}
class box2 extends box{	//해당 외부 클래스를 extends를 이용하여 상속받음
	bbb(data2){
		this.cs = data2;	
		console.log(this.cs);
		this.re = this.abc("유관순");
		console.log(this.re);
	}
}
var bx2 = new box2();
bx2.bbb("에이핑크입니다.");
var result = bx2.abc("강감찬");
console.log(result);



/*
var b = new box();
var c = b.abc("홍길동");
console.log(c);
*/
</script>
</html>