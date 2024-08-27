export class ecma_ajax{
	//formdata를 이용하여 배열생성
	arrs(){
		this.fm = new FormData();
		this.fm.append("mid","hong");
		this.fm.append("mname","홍길동");
		console.log(this.fm.get("mid"));
		console.log(this.fm.get("mname"));
		//getAll 동일한 키가 있을 경우 동일한 키 모두의 값을 출력
		//console.log(this.fm.getAll("mid"));
		this.datas = {mid:"hong",mname:"홍길동"};
		console.log(this.datas);	//배열 그자체
		console.log(JSON.stringify(this.datas));	//배열->
		문자열
		
	}
	
	
	//배열값을 전송하는 방식
	ajax_arr(){		
	//@RequestBody 형태로 Backend 처리 [],{}	
		
		//this.datas = {mid:"hong",mname:"홍길동"};
		this.datas=["hong","kim","park"];
		
		fetch("./ecma11ok.do",{
			method:"POST",
			headers:{"Content-type":"application/json"},
			//JSON.stringify: 배열을 문자열로 변경할 때 사용합니다.
			body: JSON.stringify(this.datas)
			
			/*---------Backend @ModelAttribute, @RequestParam -------------- */
			/*
			headers:{"Content-type":"application/x-www-form-urlencoded"},
			body:"mid=hong&mname=홍길동"
			*/
			/*
			body: new URLSearchParams({
				mid:"hong",
				mname:"홍길동"
			})
			*/
		}).then(function(aa){
			return aa.text();
		}).then(function(bb){
			console.log(bb);
		}).catch(function(error){
			console.log("error");
		});
				
	}


}