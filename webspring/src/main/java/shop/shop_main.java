package shop;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
//MVC
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class shop_main {
	
	@Autowired
	BasicDataSource dbinfo;
	
	PrintWriter pw = null;
	
	/* select 에서만 사용함 */
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	//'쿠폰수정'버튼 누른후 수정완료
	@PostMapping("/coupon_modifyok.do")
	public void coupon_update_golist(@ModelAttribute("coupon") coupon_dao dao,
			HttpServletResponse res) throws Exception{

		//new coupon_update().coupon_update(dbinfo, dao);
		
	}
	
	//쿠폰정보 수정버튼 (한개의 데이터만 로드 되도록 하는 메소드)
	@GetMapping("/coupon_modify.do")
	public String coupon_modify(int cidx, coupon_dao dao, Model m) throws Exception {
		coupon_update cu = new coupon_update();
		//DB정보,dao,db고유값 해당 메소드로 전달 (setter)
		cu.select_one(dbinfo,dao,cidx);
		//System.out.println(dao.list());
		
		//최종값은 배열로 getter값을 모두 제작 후 View(JSTL형태로 이관)
		m.addAttribute("info",dao.list());
		return null;
	}
	

	@GetMapping("/coupon_del.do")
	public void coupon_del(int cidx, HttpServletResponse res) throws Exception {
		String callback = new coupon_insert().del_result(dbinfo, cidx);
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();
		if(callback == "Y") {
			this.pw.print("<script>"
					+ "alert('정상적으로 쿠폰이 삭제 되었습니다.');"
					+ "location.href='./coupon_list.do';"
					+ "</script>");
		}
		else {
			this.pw.print("<script>"
					+ "alert('데이터 오류로 인하여 쿠폰이 삭제되지 않았습니다.');"
					+ "location.href='./coupon_list.do';"
					+ "</script>");
		}
		this.pw.close();
	}
	
	
	
	
	
	//쿠폰리스트 (M:dao, C:coupon_list, V:jstl)
	@GetMapping("/coupon_list.do")
	public String coupon_list(Model m) throws Exception {
		try {
			this.con = dbinfo.getConnection();
			String sql = "select cidx,cpname,cprate,cpuse from coupon order by cidx desc";
			this.ps = this.con.prepareStatement(sql);
			this.rs = this.ps.executeQuery();
			//1차배열 및 setter,getter
			coupon_dao cd = new coupon_dao();
			//2차배열
			ArrayList<ArrayList<Object>> all = new ArrayList<ArrayList<Object>>();
			int ctn = 0;
			
			while(this.rs.next()) {
				ctn = this.rs.getRow();
				//getString : 컬럼명을 사용하는경우 or 컬럼번호를 사용하는 경우 (1부터)
				cd.setCidx(Integer.parseInt(this.rs.getString(1)));
				cd.setCpname(this.rs.getString(2));
				cd.setCprate(Integer.parseInt(this.rs.getString(3)));
				cd.setCpuse(this.rs.getString(4));
				all.add(cd.list());
			}
			
			//View(JSTL로 해당 데이터를 이관)
			//데이터 총 개수
			m.addAttribute("ctn",ctn);
			//데이터 리스트 배열
			m.addAttribute("all_list",all);
			
		}catch(Exception e) {
			System.out.println("DB연결 오류 발생!");
		}finally {
			this.rs.close();
			this.ps.close();
			this.con.close();
		}
		return null;
		
	}
	
	
	
	
	//쿠폰생성
	@RequestMapping(value="/coupon_writeok.do", method=RequestMethod.POST)
	public void coupon_writeok(@ModelAttribute("coupon") coupon_dao dao,
			HttpServletResponse res) throws Exception {
		res.setContentType("text/html;charset=utf-8");
		
		//Module에서 데이터를 insert 시키며 결과값을 return 받아서 처리
		coupon_insert ci = new coupon_insert();
		//데이터베이스 정보, dao 정보를 인자값으로 이관
		String callback = ci.result(dbinfo, dao);
		this.pw = res.getWriter();
		//결과값에 맞는 조건문
		if(callback=="Y") {
			this.pw.write("<script>"
					+ "alert('정상적으로 쿠폰이 등록 되었습니다.');"
					+ "location.href='./coupon_list.do';"
					+ "</script>");
		}
		else {
			this.pw.write("<script>"
					+ "alert('데이터오류로 인하여 쿠폰이 등록되지 않았습니다.');"
					+ "history.go(-1);"
					+ "</script>");
		}
		this.pw.close();
	}
}
