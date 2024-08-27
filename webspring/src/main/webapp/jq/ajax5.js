export let data = {
	keycode:"a1234",
	ajax: function(){
		//console.log(this.keycode); //가상변수이며 출력시 this를 활용함
		
		//GET일 경우 별도의 method 사용하지 않아도 됩니다.
		fetch("/jq/rest_ajax5.do",{
			method: "GET",
			cache: "no-cache"
			//mode: "no-cors"
		})
		.then(function(node){	// Back-end에서 받는 데이터
			//return node.json(); => Back-end 에서 application/html
			return node.text();	// => Back-end 에서 application/json
		}).then(function(alldata){	// 데이터를 출력
			//application/html 일 경우 JSON.parse 로 핸들링 할 수 없습니다.
			var datas = JSON.parse(alldata);	//application/json
			console.log(datas);
		}).catch(function(error){	// 오류발생
			console.log("서버접속 오류!!");
		})
	}
	
}