//이벤트 핸들링 함수로 인자값을 받아서 처리하는 함수
export function idcheck(data){
	if(data==""){
		alert("아이디를 입력하세요!");
	}
	else{
		alert("중복되지 않은 아이디입니다.");
	}
}

export function allsum(a,b){
	console.log(a+b); //문자로 인식함
	
	var na = Number(a);
	var nb = Number(b);
	var sum = 0;
	var w = na;
	while(w<=nb){
		sum += w;
		w++;
	}
	console.log(sum);
	
	
	
	
	
	
	/*
	var n1= parseInt(no1);
	var n2= parseInt(no2);
	console.log(n1);
	console.log(n2);
	var f;
	var sum=0 ;
	for(f=n1; f<=n2; f++){
		sum += f;
		
	}
		console.log(sum);
	*/
}