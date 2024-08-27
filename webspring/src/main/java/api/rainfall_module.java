package api;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("rainfalls")
public class rainfall_module {
	@Resource(name="template")
	private SqlSessionTemplate tm;
	
	//강수량 데이터 삭제 메소드
	public int rain_delete(rainfall_dao dao) {
		int result = this.tm.delete("datadb.rainfall_delete",dao);
		return result;
	}
	
	
	//강수량 데이터 수정 메소드
	public int rain_update(rainfall_dao dao) {
		int result = this.tm.update("datadb.rainfall_update",dao);
		return result;
	}
	
	
	//일자별 리스트 값을 가져오는 메소드
	public List<rainfall_dao> ymdlist(){
		List<rainfall_dao> all = new ArrayList<rainfall_dao>();
		all = tm.selectList("datadb.all_select");
		return all;
	}
	
	
	//일일 데이터 출력메소드
	public rainfall_dao ajax_today(String today) {
		rainfall_dao dao = tm.selectOne("datadb.ajax_select2",today);
		
		return dao;
	}
	
	//ajax에 대한 데이터 체크
	public String ajax_select(String today) {	//Controller에서 인자값을 받아서 mapper로 전달
		//자료형을 String으로 받은 이유 mapper에 resultType="String"
		String result = tm.selectOne("datadb.ajax_select",today);
		
		return result;
	}
	
	
	
	//데이터 insert
	public int rain_insert(rainfall_dao dao) {
		//insert시 dao를 활용하여 setter값을 mapper로 전달하여 저장 합니다
		int result = tm.insert("datadb.rain_insert",dao);
		return result;
	}
}
