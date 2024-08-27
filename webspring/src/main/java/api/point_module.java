package api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

//point table => Module (select,insert,...)

@Repository("pointmodule") //해당 module을 controller 호출받을 이름
public class point_module {
	
	//DB연결
	@Resource(name="template")
	private SqlSessionTemplate tm;
	

	//사용자 아이디에 대한 한명의 포인트 내용
	// selectList, selectMap => 배열
	public pointdao one_list(int uidx){
		pointdao data = tm.selectOne("datadb.point_one",uidx);
		System.out.println(data.toString());
		return data;
	}
	
	
	
	//전체 포인트 내역
	public List<pointdao> all_list(){
		List<pointdao> data = new ArrayList<pointdao>();
		//selectList로 데이터 전체를 가져옴
		data = tm.selectList("datadb.point_select");
		return data;
		
	}
	
}
