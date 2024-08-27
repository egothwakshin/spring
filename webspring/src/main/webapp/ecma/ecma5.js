//로그인 class
export class login{
	//this로 선언된 가상변수(static적용된 사항과 같음)
	constructor(...data){	//setter 역할
		//this.mid = "hong";
		console.log(data);	
		this.mid = data[0];
		this.mpass = data[1];
		
	}
	login_check(){
		//console.log(this.mid);	//즉시실행안에 있는 변수("hong")를 받아 오는게 가능함을 콘솔로 확인.
		console.log(this.mid);
		console.log(this.mpass);
		if(this.mid==""|| this.mpass==""){
			alert("아이디 및 패스워드를 모두 입력하셔야 합니다.");
		}
		else{
			/*
			this.f = document.querySelector("#frm");
			this.f.submit();
			*/
			return;
		}
	}
	
	
	
}


export class tels{
	constructor(){		//class호출시 즉시실행되는 메소드
		console.log("test");
	}
	
	agree_data2(){	//setter
		this.data = "홍길동";
		
	}
	
	get agree_data(){	//getter : get을 생략해도 상관없음
		return this.agree();
	}
	
	
	agree(){
		return "Y";
	}
	
	
	
	tel_check(){
		this.tel1 = document.querySelector("#tel1").value;
		this.tel2 = document.querySelector("#tel2").value;
		this.tel3 = document.querySelector("#tel3").value;
		this.total = this.tel1 + this.tel2 + this.tel3
		if(isNaN(this.total)==false){ //isNaN(=숫자가 아님)== false : 숫자 맞음
			alert("인증번호가 발송되었습니다.");	
			this.tel_random();	//class 안에 있는 메소드 호출
		}
		else{	//숫자가 아님
			alert("정상적인 휴대폰 번호를 입력하세요");
		}
	}
	tel_random(){		//랜덤함수를 이용하여 작동되는 메소드
		this.w = 1;
		this.code="";
		while(this.w<=6){
			this.code += Math.floor(Math.random()*10);
			this.w++;
		}
		console.log(this.code);
	}
	
}