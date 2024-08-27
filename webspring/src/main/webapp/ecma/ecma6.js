export class movieck{
	input_check(){	//사용자가 입력하는 필수값만 모두 검토하는 메소드
		if(frm.msubject.value==""){
			alert("영화 제목을 입력하세요");
			frm.msubject.focus();
		}
		else if(frm.cinema.value==""){
			alert("영화관을 선택하세요");
			frm.cinema.focus();
		}
		else if(frm.ticketing.value==""){
			alert("예매가격을 선택하세요");
			frm.ticketing.focus();
		}
		else{
			this.input_exp();		
		}
	}
	input_exp(){
		if(isNaN(frm.ticketing.value)==false){
			frm.submit();
		}
		else{
			alert("예매가격은 숫자만 입력가능합니다.");
		}
	}
	
	
}