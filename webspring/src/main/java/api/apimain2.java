package api;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

//Mybatis(ibatis)를 이용하여 DAO + config, mapper.xml DB연결
@Controller
public class apimain2 {

	@Inject
	private SqlSessionFactory sqlfact;
	SqlSession se = null;
	
	
	@Resource
	private SqlSessionTemplate template;
	
	
	PrintWriter pw = null;
	
	@GetMapping("/point_delete.do")	
	public String deleteok(int uidx, HttpServletResponse res) {	
		res.setContentType("text/html;charset=utf-8");
		
		try {
			this.se = this.sqlfact.openSession();
			int result = this.se.delete("datadb.point_del", uidx); // result = 1 (성공)
			this.pw = res.getWriter();
			if(result > 0) {
				this.pw.print("<script>"
						+ "alert('데이터 삭제가 정상적으로 완료되었습니다.');"
						+ "location.href='./point_list.do';"
						+ "</script>");
			}
		}catch(Exception e) {
			System.out.println(e);
			System.out.println("DB 연결오류!!");
		}finally {
			this.se.close();
		}
		
		return null;
	}
	
	
	@GetMapping("/point_person.do")
	public String point_person(Model m, int uidx) {
		try {
			this.se = this.sqlfact.openSession();
			//selectOne("mapper정보", 인자값)
			pointdao pd = this.se.selectOne("datadb.point_one",uidx);
			ArrayList<Object> onedata = new ArrayList<Object>();
			onedata.add(pd.getUidx());
			onedata.add(pd.getUid());
			onedata.add(pd.getUpoint());
			onedata.add(pd.getUname());
			onedata.add(pd.getUdate());
			System.out.println(onedata);
		}catch(Exception e) {
			System.out.println(e);
			System.out.println("DB 연결오류!!");
		}finally {
			this.se.close();
		}
		return null;
	}
	
	
	
	@GetMapping("/point_list.do")
	public String point_list(Model m) {
		List<pointdao> all = null;	//getter,setter로 이루어진 배열
		try {
			this.se = this.sqlfact.openSession();
			//selectList : 여러개의 데이터를 dao로 이용하여 출력하는 방식
			//selectOne: 하나의 데이터를 dao를 이용하여 출력하는 방식
			all = this.se.selectList("datadb.point_select");
			m.addAttribute("all",all);	//view jsp로 전달
		}catch(Exception e) {
			System.out.println(e);
			System.out.println("Database 접속오류 발생!!");
		}finally {
			this.se.close();
		}
		
		return null;
	}
	
	
	
	@PostMapping("/pointok.do")
	public String pointok(@ModelAttribute("point") pointdao dao, HttpServletResponse res) {
		res.setContentType("text/html;charset=utf-8");
		try {
			this.se = this.sqlfact.openSession();
			this.se.insert("datadb.point_insert",dao);
			this.pw = res.getWriter();
			this.pw.print("<script>"
					+ "alert('정상적으로 지급완료 되었습니다.');"
					+ "location.href='./point_list.do';"
					+ "</script>");
		}catch(Exception e) {
			System.out.println(e);
			System.out.println("DB 접속 오류!!");
		}finally {
			this.se.close();
		}
		return null;
	}
	
	
	
	@GetMapping("/test_insert.do")
	public String test_insert(cpdao dao) {
		try {
			dao.setCpname("2024년 연말정산 40%할인");
			dao.setCprate(40);
			dao.setCpuse("Y");
			dao.setCpdate("2024-12-31");
			this.se = this.sqlfact.openSession();
			this.se.insert("datadb.coupon_insert",dao);
			this.se.close();
			
		}catch(Exception e) {
			System.out.println("Database 연결실패!!");
		}
		return null;
	}
}
