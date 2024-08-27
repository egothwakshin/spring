package api;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class apimain {
	
	/*
	@Inject		//Module 에서도 활동가능
	private BasicDataSource dataSource2;	//dbcon.java (@Bean)
	*/
	
	@Autowired
	BasicDataSource dbinfo; //dbconfig.xml	
	PrintWriter pw = null;
	
	@GetMapping("/testsql.do")
	public String testsql() {
		
		return null;
	}
	
	/*
	//DB연결테스트
	@GetMapping("/beantest.do")
	public String beantest() {
		try {
			Connection con = dataSource2.getConnection();
			System.out.println(con);
			con.close();
		}catch(Exception e) {
			System.out.println("Bean DB연결 실패!!");
		}
		return null;
	}
	*/
	
	/*
		REST :	
		1. WEB으로 배열을 출력하는 형태: HTML, TEXT
		2. 웹 디렉토리에 File(.json)을 생성하여 Front-end가 데이터를 가져가는 형태: JSON
		   예시) 회원가입 페이지 -> 회원가입 완료 -> json 생성
		   예시) 상품등록 페이지 -> 상품등록 완료 -> json 생성
	*/
	@SuppressWarnings("unchecked")
	
	
	//res.addHeader 없이 사용가능한 어노테이션
	//@CrossOrigin(origins="*", allowedHeaders = "*") // 
	//@GetMapping("/jq/rest_json3.do")
	@GetMapping(value="/jq/rest_json3.do", produces="application/json")
	public String rest_json3(Model m, HttpServletResponse res, HttpServletRequest req) throws Exception {
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.addHeader("Access-Control-Allow-Credentials", "true");
		
		Object data[][] = {
				{"선풍기",50000},
				{"치약",5000}
		};
		JSONArray ja = new JSONArray();
		int w = 0;
		while(w<data.length) {
			JSONObject jo = new JSONObject();
			jo.put("product_nm", data[w][0]);
			jo.put("product_money", data[w][1]);
			ja.add(jo);
			w++;
		}
		
		JSONObject jo2 = new JSONObject();
		jo2.put("product", ja);
		//m.addAttribute("arr",jo2);
		/*
		this.pw = res.getWriter();
		this.pw.print(jo2);
		 */
		m.addAttribute("data",jo2);
		return "/jq/rest_json3";
	}
	
	//URL에서 JSON으로 호출 (produces: 해당 mapping의 속성을 변환 시키는 데이터 타입 (기본: html) )
	@GetMapping(value="/jq/rest_json2.do", produces="application/json")
	public String rest_json2(Model m) throws Exception {
		
		JSONArray ja = new JSONArray();		
		ja.add("hong");
		ja.add("park");
		ja.add("kim");
		JSONObject jo = new JSONObject();
		jo.put("member", ja);
		m.addAttribute("data", jo);
		return "/jq/rest_json";
	}
	
	
	
	
	
	//json 파일 생성하는 URL
	@GetMapping("/json_make.do")
	public String json_make(HttpServletResponse res, HttpServletRequest req) {
		res.setContentType("text/html;charset=utf-8");
		try {
			this.pw = res.getWriter();
			JSONArray ja = new JSONArray();		
			ja.add("hong");
			ja.add("park");
			ja.add("kim");
			JSONObject jo = new JSONObject();
			jo.put("member", ja);
			String url = req.getServletContext().getRealPath("/upload/");
			
			FileWriter fw = new FileWriter(url + "test2.json");
			fw.write(jo.toJSONString());	//JSON파일 생성
			fw.flush();
			fw.close();
			this.pw.write("<script>alert('회원가입 완료!!');</script>");
			
		}
		catch(Exception e) {
			this.pw.write("<script>alert('error');</script>");
		}
		finally {
			this.pw.close();
		}
		return null;
	}
	
	@GetMapping("/jq/rest_json.do")
	public String rest_json(Model m, HttpServletResponse res) throws Exception {
		//JSONObject: {}, JSONArray: []
		JSONArray ja = new JSONArray();		
		ja.add("hong");
		ja.add("park");
		ja.add("kim");
		JSONObject jo = new JSONObject();
		jo.put("member", ja);
	
		m.addAttribute("data",jo);
		return "/jq/rest_json";
	}
	
	
	
	PreparedStatement ps = null;
	ResultSet rs = null;
	//javascript ajax Mapping
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/jq/rest_ajax4.do")
	public String rest_ajax4(String keycode, HttpServletResponse res) throws Exception {
		this.pw = res.getWriter();
		if(keycode==null||keycode==""||!keycode.equals("a1234")) {
			this.pw.print("인증키가 확인되지 않습니다.");
		}
		else {
			try{
				Connection con = dbinfo.getConnection();
				String sql = "select * from coupon order by cidx desc";
				this.ps = con.prepareStatement(sql);
				this.rs = this.ps.executeQuery();
				
				JSONArray ja = new JSONArray();
				while(rs.next()) {
					JSONObject jo = new JSONObject();
					jo.put("cidx", this.rs.getString(1));
					jo.put("cpname", this.rs.getString(2));
					jo.put("cpuse", this.rs.getString(4));
					jo.put("cpdata",this.rs.getString(5));
					ja.add(jo);
				}
				JSONObject jo2 = new JSONObject();
				jo2.put("coupon", ja);
				this.pw.print(jo2);
							
			}catch(Exception e) {
				this.pw.print("error");
			}finally {
				rs.close();
				ps.close();
			}
		}
		this.pw.close();
		return null;
	}
	
	
	//ECMA Ajax 맵핑부분
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(value="/jq/rest_ajax5.do",produces = "application/json")
	public String rest_ajax5(HttpServletResponse res) throws Exception {
		this.pw = res.getWriter();
		
		try {
			Connection con = dbinfo.getConnection();
			String sql = "select uidx,uid,uname,ujoin from user order by uidx desc";
			this.ps = con.prepareStatement(sql);
			this.rs = this.ps.executeQuery();
			
			JSONArray all_ja = new JSONArray();
			while(this.rs.next()) {
				JSONArray ja2 = new JSONArray();
				ja2.add(this.rs.getString(1));
				ja2.add(this.rs.getString(2));
				ja2.add(this.rs.getString(3));
				ja2.add(this.rs.getString(4));
				all_ja.add(ja2);
			}
			con.close();
			this.pw.print(all_ja);
			
		}catch(Exception e) {
			this.pw.print("error");
		}finally {
			rs.close();
			ps.close();
		}		
		this.pw.close();
		return null;
	}
	
	
	
}
