package api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("notice")
public class notice_module {
	
	@Resource(name="template2")
	private SqlSessionTemplate tm;
	
	//선생님코드 게시판 선택삭제
	public int del_notice(String nidx) {
		/*
		 //mapper에서 foreach 로 처리
		System.out.println(nidx);
		String list[] = nidx.split(",");
		int callback = tm.delete("noticeDB.notice_delete",list);
		*/
		
		//FIND_IN_SET으로 처리
		int callback = tm.delete("noticeDB.notice_delete2",nidx);
		
		return callback;
	}
	
	/*
	//내 코드 게시판 선택삭제
	public String select_del(String ck[]) {
		System.out.println(ck.length);
		int w=0;
		while(w<ck.length) {
			tm.delete("noticeDB.notice_select_del", ck[w]);
			w++;
		}		
		return null;
	}
	*/
	
	
	//게시판 전체 게시물 메소드
	public List<notice_dao> alldata(){
		List<notice_dao> nd = new ArrayList<notice_dao>();		
		nd = tm.selectList("noticeDB.notice_all");
		return nd;
	}
	
	//검색하는 게시물 메소드
	public List<notice_dao> alldata(String search_part, String search_word){
		
		List<notice_dao> nd = new ArrayList<notice_dao>();		
		//mapper에 인자값은 단 한개의 값만 적용할 수 있습니다.
		//해결법 키배열을 이용한 형태로 전달시 해당 mapper에서 핸들링 할 수 있음
		//selectOne, selectList, insert 등 모든 클래스에 한함
		Map<String, String> m = new HashMap<String, String>();
		m.put("search_part", search_part);
		m.put("search_word", search_word);
		nd = tm.selectList("noticeDB.notice_search",m);		
		return nd;
	}
}
