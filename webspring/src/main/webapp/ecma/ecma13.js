//외부 서버에 데이터를 삭제 또는 추가를 요청하는 class

/*
외부에서 Delete, Put에 바로 접근이 불가능 한 상황임. 해당 부분을 POST로 전송 후
Back-end에서 Delete, Put 으로 전달 후 결과값을 return 으로 출력

Delete, Put 접근은 가능하지만, return 결과를 받지 못함
*/
export class host_call{
	delete_call(){
		this.no = "15";
		//ajax로 데이터 송신
		fetch("./delete.do/"+this.no,{		
			method:"delete",	//데이터 삭제 API 전송방식
			headers:{"content-type":"application/x-www-form-urlencoded"},
		})
		.then(function(aa){
			return aa.text();	//결과값 리턴을 문자화
		})
		.then(function(bb){
			//Promise: 배열 형태의 결과값 => o, k
			console.log(bb);
		}).catch(function(error){
			console.log(error);
			console.log("server error!!");
		})
	}
}

export class data_call{
	
	//데이터 삭제 요청 메소드
	delete_call(){
		this.no = ["10","20","30","40","50"];
		
		//ajax로 데이터 송신
		fetch("./ecma13put.do/"+this.no,{		
			//method:"delete",	//데이터 삭제 API 전송방식
			method:"put",
			headers:{"content-type":"application/x-www-form-urlencoded"},
		})
		.then(function(aa){
			return aa.text();
		})
		.then(function(bb){
			console.log(bb);
		}).catch(function(error){
			console.log(error);
			console.log("server error!");
		})
		
	}
	
}