package webspring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import shop.member_dao;

@Controller
public class mainpage2 {
	//@RequestMapping (value="가상의URL", method=RequestMethod.GET또는POST)
	@RequestMapping(value="/admin/adminok.do", method=RequestMethod.GET)
	public String adminok(HttpServletRequest req, Model m) {
		String pidx = req.getParameter("pidx");
		System.out.println(pidx);
		return null;
	}
	/*@RequestMapping 사용시 form에서 name값 전송시 Spring은 인자값으로 모든 핸들링이 가능함
		받아올 name값을 인자값에 넣었을 경우, 무조건 필수로 값을 전달받아서 처리해야함	*/
	/*@RequestParam : 인자값에 적용하는 어노테이션. 값이 없을 경우(null일경우)
		defaultValue를 이용하여 디폴트(default) 값을 적용할수있음
		required = true (name값 필수로 받아야함)
		required = false (name값 필수로 받아야할 필요 없음) */
	@RequestMapping(value="/admin/telok.do", method=RequestMethod.POST)
	public String telok(@RequestParam(defaultValue = "N", required = false) String agree,
			String tel, String email[]) {
		System.out.println(tel);
		System.out.println(email[0]);
		System.out.println(email[1]);
		System.out.println(agree);
		return null;
	}
	
	//Database연결(Spring) - XML형태의 DB연결
	//1.XML형태의 DB연결, 2.Properties+JSTL(legacy 프로젝트)
	/*
	 [XML] 형태 DB연결시 필요한 라이브러리 (필수)
	 1. MysqlConnect/J
	 2. spring-jdbc: @ 이용하여 Resource 형태로 구성 
	 3. 
	*/
	
	/*
	 Autowired 단점
	 1. 외부 class를 호출하여 Database를 연결시 오류발생
	 2. Autowired는 Controller 에서만 사용가능함 (Module에서 사용 X)
	 */
	@Autowired //모든 메소드에 사용할 수 있는 객체 및 Bean을 사용할 수 있도록 의존성 여부 실행
	BasicDataSource dbinfo;	//Database접속정보 xml 파일로드
	
	@GetMapping("/datalist.do")
	public String datalist() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = this.dbinfo.getConnection();
			String sql = "select count(*) as ctn from user";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			System.out.println(rs.getString("ctn"));
			ps.close();
			rs.close();
		}catch(Exception e) {
			System.out.println(e);
			System.out.println("Database 접속오류!!");
		}
		return null;
	}
	
	//name값을 배열로받을 경우 필수로 값을 받겠다라는 어노테이션을 사용하지 못함
	/* datasource를 이용하여 해당 정보를 DB에 insert 하시오. */
	@RequestMapping(value="/memberok.do",method=RequestMethod.POST)
	//@ModelAttribute : DAO와 함께 사용하는 형태의 클래스
	public String memberok(@ModelAttribute("mb") member_dao dao) {
		System.out.println(dao.getUid());
		System.out.println(dao.getUname());
		System.out.println(dao.getUpass());
		try {
			Connection con = dbinfo.getConnection();
			String sql = "insert into user values('0',?,?,?,now())";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dao.getUid());
			ps.setString(2, dao.getUpass());
			ps.setString(3, dao.getUname());
			ps.executeUpdate();
			ps.close();
		}catch(Exception e) {
			System.out.println("Database 접속오류!!");
		}
		return null;
	}
	
}
