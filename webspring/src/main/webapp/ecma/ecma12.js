var arr_data = new Array();	//[] 최초 빈배열
var arr_data2 = new FormData();	// {} 최초 빈배열
var arr_data3 = {
			"mid":"hong",
			"mname":"홍길동",
			"mage":"55",
			"marea":"서울",
			"mtel":"01022223333"	
			};

export class test_ajax{
	//array
	array_make(z){
		arr_data.push(z);
		console.log(arr_data);
	}
	ajax_post(){	//array로 생성된 데이터를 POST 전송

		
		fetch("http://172.30.1.22:8080/ECMA/ecma12ok_array.do",{
			method:"POST",
			//headers:{"Content-type":"application/x-www-form-urlencoded"},
			headers:{"Content-type":"application/json"},
			mode: "no-cors",	//mode를 이용하여 CROS 해제 및 전송
			body: JSON.stringify(arr_data)
		}).then(function(aa){
			return aa.text();
		}).then(function(bb){
			console.log(bb);
		}).catch(function(error){
			console.log("error");
		});
	}
	
	
	//formdata
	formdata_make(...z){
		//arr_data2.append(z[0],z[1]);
		arr_data2.append("test1","t12321");
		arr_data2.append("test2","t12321");
		arr_data2.append("test3","t12321");
		arr_data2.append("test4","t12321");
	}
	ajax_post2(){	//formdata로 생성된 데이터를 POST 전송
		this.pt = JSON.stringify(arr_data3);
		console.log(this.pt);
		
		fetch("./ecma12ok.do",{
			method:"POST",
			headers:{"Content-type":"application/json"},
			body: this.pt
		}).then(function(aa){
			return aa.text();
		}).then(function(bb){
			console.log(bb);
		}).catch(function(error){
			console.log("error");
		});
	}
	
	
}