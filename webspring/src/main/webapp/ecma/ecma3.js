var dd = "테스트 ECMA 코드";
//import에서 요청하는 사항을 export를 이용하여 값을 내보냄
export default console.log(dd);

//abc 함수를 import 호출할수 있도록 export로 설정
export function abc(data){
	return console.log(data + "님 환영합니다.");
}

//이벤트 핸들링 함수 (this: 가상변수를 선언할 수 있는 this 명령어)
export function bbb(){
	this.no1 = 5;	//가상변수(setter)
	this.no2 = 20;	//가상변수(setter)
	return console.log(this.no1 * this.no2);
	location.href="http://naver.com";
}