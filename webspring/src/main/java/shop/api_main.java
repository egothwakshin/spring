package shop;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.json.simple.*;

@Controller
public class api_main {
	@Autowired
	BasicDataSource dbinfo;
	PrintWriter pw = null;
	
	//JSON 데이터베이스 API
	@GetMapping("/ecma/ecma14ok.do")
	public String ecma14ok(Model m, coupon_dao dao) throws Exception {
		/*
		JSONArray ar = new JSONArray();
		ar.put("홍길동");
		ar.put("강감찬");
		ar.put("이순신");
		JSONObject jo = new JSONObject();
		jo.put("member", ar);
	
		String result = ar.toString();
		String result2 = jo.toString();
		
		m.addAttribute("data",result);
		m.addAttribute("data2", result2);
		*/
		
		/*
		 JSONArray [] 대괄호,
		 JSONObject {} 중괄호
		*/
		/*
		JSONArray ar1 = new JSONArray();
		ar1.put("hong");
		ar1.put("kim");
		JSONArray ar2 = new JSONArray();
		ar2.put("22");
		ar2.put("33");
		JSONArray ar3 = new JSONArray();
		ar3.put(ar1);
		ar3.put(ar2);
		
		String result = ar3.toString();
		*/
		
		/*
		JSONObject jo = new JSONObject();
		jo.put("img", "abc.jpg");
		jo.put("title", "핸드폰");
		JSONArray ar = new JSONArray();
		ar.put(jo);
		JSONObject jo2 = new JSONObject();
		jo2.put("best_product", ar);
		
		String result = jo2.toString();
		*/
		
		/*
		JSONArray ar1 = new JSONArray();
		ar1.put("테스트1");
		
		JSONArray ar3 = new JSONArray();
		ar3.put(ar1);
		
		ar1 = new JSONArray();
		ar1.put("테스트2");
		ar3.put(ar1);
		
		JSONObject jo = new JSONObject();
		jo.put("review", ar3);
		
		String result = jo.toString();
		*/
		/*
		ArrayList<Object> al = new ArrayList<Object>();
		al.add("테스트1");
		al.add("테스트2");
		
		JSONObject jo = new JSONObject();
		JSONArray ja2 = new JSONArray();
		int w = 0;
		while(w<al.size()) {
			JSONArray ja = new JSONArray();
			ja.put(al.get(w));
			ja2.put(ja);
			w++;
		}
		jo.put("product", ja2);
		String result = jo.toString();
		*/
		/*		 
		 //하다가 중단됨. 내가 완성해야될 코드
		ArrayList<Object> al = new ArrayList<Object>();
		ArrayList<Object> al2 = new ArrayList<Object>();
		al.add(1);
		al.add("apples");
		al.add("홍길동");
		al2.add(2);
		al2.add("apink");
		al2.add("에이핑크");
		
		JSONObject jo = new JSONObject();
		int w = 0;
		while(w<2) {
			JSONObject jo2 = new JSONObject();
			jo2.put("midx", al[w].get(0));
			jo2.put("user_id", false);
			jo2.put("user_name", false);
			w++;
		}
		*/
		
		/*
		ArrayList<ArrayList<String>> all = new ArrayList<ArrayList<String>>();
		
		ArrayList<String> al = new ArrayList<String>();
		al.add("1");
		al.add("apple");
		al.add("홍길동");
		all.add(al);
		al = new ArrayList<String>();
		al.add("2");
		al.add("apink");
		al.add("에이핑크");
		all.add(al);
		
		JSONArray ja = new JSONArray();
		int w = 0;
		
		while(w<all.size()) {
			JSONObject jo = new JSONObject();
			jo.put("midx", all.get(w).get(0));
			jo.put("user_id", all.get(w).get(1));
			jo.put("user_name", all.get(w).get(2));
			ja.put(jo);
			w++;
		}
		String result = ja.toString();
		*/
		
		Connection con = dbinfo.getConnection();
		String sql = "select * from coupon order by cidx desc";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		JSONArray ja = new JSONArray();
		while(rs.next()) {
			JSONObject jo = new JSONObject();
			jo.put("cidx", rs.getString(1));
			jo.put("cpname", rs.getString(2));
			jo.put("cprate", rs.getString(3));
			jo.put("cpuse", rs.getString(4));
			jo.put("cpdate", rs.getString(5));
			jo.put("indate", rs.getString(6));
			ja.put(jo);
		}
		
		String result= ja.toString();
		m.addAttribute("data",result);
		rs.close();
		ps.close();
		con.close();
		return "/ecma/ecma14ok";
	}
	
	
	
	//외부서버에서 요청한 정보를 바탕으로 데이터를 삭제하는 형태
	@DeleteMapping("/ecma/delete.do/{no}")
	public void deleteok(@PathVariable String no, HttpServletResponse res) throws Exception {
		this.pw = res.getWriter();
		//외부서버요청은 특정 서버만 핸들링될 수 있도록 해야함
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.addHeader("Access-Control-Allow-Credientials", "true");
		if(no==null||no=="") {
			this.pw.print("error");
		}
		else {
			String result = this.ecma13(no, res);
			this.pw.print(result);
		}
	}
	
	
	//데이터를 insert 요청시 사용하는 맵핑
	@PutMapping("/ecma/ecma13put.do/{no}")
	public String ecma13put(@PathVariable String no,
			HttpServletResponse res)throws Exception {
		
		this.pw = res.getWriter();
		String datas[] = no.split(",");
		System.out.println(datas.length);
		this.pw.print("ok");
		return null;
	}
	
	//database에 해당 정보를 삭제 요청하는 Restful 서비스로 요청 받은 후 DB 데이터 삭제
	@DeleteMapping("/ecma/ecma13.do/{no}")
	public String ecma13(@PathVariable String no,
			HttpServletResponse res) throws Exception {
		this.pw = res.getWriter();
		//한개 또는 여러개의 데이터를 삭제할 수 있으므로 배열로 구분하여 처리
		String datas[] = no.split(",");
		System.out.println(datas.length);
		this.pw.print("ok");
		this.pw.close();
		
		return null;
	}
	


	@PostMapping("/ecma/ecma12ok.do")
	public String ecma12_arr(@RequestBody String data, HttpServletResponse res) throws Exception{
		//res.setContentType("text/json;charset=utf-8");
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.addHeader("Access-Control-Allow-Credientials", "true");
		System.out.println(data);
		this.pw = res.getWriter();
		this.pw.print("ok");
		
		return null;
	}
	
	/*
	public String ecma11(@ModelAttribute data_dao dao,HttpServletResponse res) throws Exception {
	System.out.println(dao.getMid());
	System.out.println(dao.getMname());
	 */
	/*
	public String ecma11(@RequestParam String mid,
			@RequestParam String mname,
			HttpServletResponse res) throws Exception {
	*/
	//@RequestParam(자료형), @ModelAttribute(dto), @RequestBody(배열자료형,변수형 : 데이터값을 전체로 받아서 처리 )
	@PostMapping("/ecma/ecam11ok.do")
	public String ecma11(@RequestBody String data, HttpServletResponse res) throws Exception{
		this.pw = res.getWriter();
		
		try {
			//Front-end에서 넘어오는 배열의 괄호 종류에 따라서
			//JSONArray: [] 배열, JSONObject: {} 배열
			/*
			JSONObject jo = new JSONObject(data);	// {} 배열받음
			System.out.println(jo.get("키값"));
			*/
			JSONArray jo = new JSONArray(data);	// [] 배열받음
			System.out.println(jo.get(0));
			this.pw.print("ok");
		}catch(Exception e) {
			this.pw.print("no");
		}
		
		return null;
	}
	
	
	//ecma ajax (POST 통신)
	@PostMapping("/ecma/ecma9ok.do")
	public String ecma9(@RequestParam String mid, HttpServletResponse res) throws Exception {
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.addHeader("Access-Control-Allow-Credientials", "true");
		
		System.out.println(mid);
		this.pw = res.getWriter();
		this.pw.print("뭘봐");
		
		this.pw.close();
		return null;
	}
	
	
	
	//ecma ajax (GET 통신)
	@GetMapping("/ecma/ecma8ok.do")
	public String ecma8(@RequestParam String mid, HttpServletResponse res) throws Exception {
		this.pw = res.getWriter();
		if(mid==null) {
			this.pw.print("error");
		}
		else {
		Connection con = dbinfo.getConnection();
		String sql = "select count(*) as ctn from login where mid=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, mid);
		ResultSet rs = ps.executeQuery();
		rs.next();
			if(Integer.parseInt(rs.getString("ctn"))>0) {
				pw.print("no");
			}
			else {
				pw.print("ok");
			}	
		rs.close();
		ps.close();
		}	
		this.pw.close();
		return null;
	}
	
	//스트리밍 CDN API
	@GetMapping("/youtube/{id}")
	public @ResponseBody byte[] youtube(@PathVariable String id, HttpServletRequest req) throws Exception {
		//웹 디렉토리 경로
		String url = req.getServletContext().getRealPath("/upload/");
		
		//URL에 있는 파라미터를 받아서 해당 동영상을 byte로 변환하여 결과를 return
		String filename = url + id + ".mp4";
		InputStream is = new FileInputStream(filename);
		byte[] movie = IOUtils.toByteArray(is);
		is.close();
		return movie;
	}
	
	
	
	//CDN 유의사항: 파일명이 같은데 속성(확장자)이 다를 경우 오작동 발생
	//CDN API Service (@PathVariable: Mapping에 있는 파라미터 이름을 로드하여 변수로 변환하는 역할
	@GetMapping("/images/{name:[0-9a-z]+}")	//정규식 코드 형태로 구성을 하게 되면, 문자형태의 이미지는 출력하지 않음.
	/*
	 REST
	 @ResponseBody: http 프로토컬 주소를 html에서 로드시 자동 맵핑역할을 하여 결과값을 return 해서 사용하는 형태(이미지,xml,json)
	 @RequestBody: ajax로 요청시 해당 내용을 맵핑하여 출력시키는 역할
	 */
	public @ResponseBody byte[] images(@PathVariable String name, HttpServletRequest req) throws Exception{
		String webpath = req.getServletContext().getRealPath("/upload/");
		String url = webpath + name + ".jpg";
		InputStream is = new FileInputStream(url);
		//IOUtils=> 파일 읽기,쓰기를 사용할 수 있음 (IOUtils,toString(), copy)
		byte[] imgurls = IOUtils.toByteArray(is);	//byte형식으로 한번에 데이터를 읽어서 자료형 전송
		is.close();
		
		return imgurls;	//
	}
	
	@GetMapping("/ecma/ecma7.do")
	public String ecma7(Model m, movie_dao dao) throws Exception{
		try {
			Connection con = dbinfo.getConnection();
			String sql = "select * from movies order by midx desc";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			ArrayList<ArrayList<Object>> all = new ArrayList<ArrayList<Object>>();
			int count = 0;
			while(rs.next()) {
				count = rs.getRow();
				dao.setMidx(Integer.parseInt(rs.getString(1)));
				dao.setMsubject(rs.getString(2));
				dao.setCinema(rs.getString(3));
				dao.setTicketing(Integer.parseInt(rs.getString(4)));
				dao.setScreen_date(rs.getString(5));
				all.add(dao.data());	// getter 1차배열값을 2차배열로 이관
			}
			m.addAttribute("all",all);	//데이터 2차배열
			m.addAttribute("count",count);	//데이터 총개수
			rs.close();
			ps.close();
			
		}catch(Exception e) {
			m.addAttribute("error",e);
		}
		return null;
	}
	
	
	
	@PostMapping("/ecma/ecma6ok.do")
	public void ecma6(@ModelAttribute("dao") movie_dao dao, HttpServletResponse res) throws Exception {
		this.pw = res.getWriter();
		res.setContentType("text/html;charset=utf-8");
		try {
			Connection con = dbinfo.getConnection();
			String sql = "insert into movies values('0',?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dao.getMsubject());
			ps.setString(2, dao.getCinema());
			ps.setInt(3, dao.getTicketing());
			ps.setString(4, dao.getScreen_date());
			ps.executeUpdate();
			ps.close();
			this.pw.print("<script>"
					+ "alert('정상적으로 등록 완료되었습니다.');"
					+ "location.href='./ecma7.do';"
					+ "</script>");
			
		}catch(Exception e) {
			this.pw.print("<script>"
					+ "alert('DB오류발생!!');"
					+ "history.go(-1);"
					+ "</script>");
		}
	}
}
