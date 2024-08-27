export class logins{
	//ajax를 사용하는 메소드 (GET통신)
	ajax_idck(id){
		this.mid = id;

		//fetch : ecma에서부터는 XMLHttpReqest => fetch로 변환됨
		fetch("./ecma8ok.do?mid="+id)
		.then(function(aa){	//then 서버에서 응답받은 값을 처리하는 역할
			return aa.text();
		})
		.then(function(bb){	//then 서버에서 받은 값을 출력하는 역할
			//ok: 사용가능한 아이디, no: 사용 불가능한 아이디, error: 전송오류
			
			console.log(bb);
		})
		.catch(function(error){	//예외처리로 오류발생시 출력되는 역할
			console.log("통신오류 발생");
		});
		
		
	}
}