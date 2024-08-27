//사용자가 선택한 영상을 처리하는 class
export class m_select{
	m_load(n){
		//파라미터 값으로 해당 페이지를 실행시킴
		location.href='./youtube.jsp?vidx='+n;
	}
	
}