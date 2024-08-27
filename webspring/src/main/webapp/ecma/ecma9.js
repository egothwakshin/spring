//ajax post통신 코드
//application/x-www-form-urlencoded
export class logins{
	ajax_idck(id){
		this.mid = id;
		this.mid2 = "mid="+id;
		/*
		post 통신은 method 속성을 post 적용 및 headers에 application을 입력 후
		배열이 아닐 경우 ?를 이용하여 GET과 비슷하게 전송을 하는 방식
		
		또는 가상의 변수를 이용하여 Backend가 받는 파라미터 이름을 이용하여 body 속성에
		값을 적용하는 보내는 방식도 있음
		단, 배열값을 전송시에는 body: {} 와같이 중괄호가 필수임.
		*/
		fetch("http://172.30.1.20:8080/ecma/ecma9ok.do",{
			method: "POST",
			headers: {"content-type":"application/x-www-form-urlencoded"},
			body: this.mid2
			//body:{"mid":this.mid}
		})
		.then(function(aa){
			return aa.text();
		})
		.then(function(bb){
			console.log(bb);
		})
		.catch(function(error){
			console.log("통신오류!!");
		});
	}
	
}