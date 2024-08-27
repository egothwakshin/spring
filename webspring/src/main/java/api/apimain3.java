package api;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class apimain3 {
	
	//해당 프로젝트에 대한 module 호출 (@Repository)
	@Resource(name="pointmodule")
	private point_module pm;	//해당 module을 필드에 객체생성 후 적용
	
	
	@Resource(name="rainfalls")
	private rainfall_module rm;
	
	PrintWriter pw = null;
	
	
	//강수량 데이터삭제(delete)
	@GetMapping("/rainfall/rainfall_delete.do")
	public String rainfall_delete(@ModelAttribute("rainfall") rainfall_dao dao) throws Exception {
		this.rm.rain_delete(dao);
		return null;
	}
	
	
	//강수량 데이터수정 (update)
	@PostMapping("/rainfall/rainfall_updateok.do")
	public String rainfall_updateok(@ModelAttribute("rainfall") rainfall_dao dao, HttpServletResponse res) throws Exception {
		res.setContentType("text/html;charset=utf-8");
		this.pw=res.getWriter();
		int callback = this.rm.rain_update(dao);
		if(callback>0) {
			this.pw.print("<script>"
					+ "alert('정상적으로 데이터가 수정되었습니다.');"
					+ "location.href='./rainfall_list.do';"
					+ "</script>");
		}
		else {
			this.pw.print("<script>"
					+ "alert('데이터 오류로 수정이 완료되지 않았습니다.');"
					+ "location.href='./rainfall_list.do';"
					+ "</script>");
		}
		this.pw.close();
		return null;
	}
	
	//수정페이지로 가는 
	@GetMapping("/rainfall/rainfall_modify.do")
	public String rainfall_modify(String sdate,HttpServletResponse res,Model m) throws Exception {
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();
		if(sdate==null||sdate.equals("")) {
			this.pw.print("<script>"
					+ "alert('올바른 접근이 아닙니다.'); history.go(-1);"
					+ "</script>");
			this.pw.close();
		}
		else {
			rainfall_dao dao = this.rm.ajax_today(sdate);
			m.addAttribute("data", dao);
		}
		
		return "/rainfall/rainfall_modify";
	}
	
	
	
	//select => option에서 ajax값을 받은 후 데이터를 Front에 전달
	//저장된 모든 데이터를 List dao로 데이터를 가져옴(SelectList)
	
	//** ajax(SPA 형식)일 경우 pw.close의 위치에 따라서 해당 view페이지가 출력되지 않음
	@GetMapping("/rainfall/rainfall_list.do")
	public String rainfall_list(Model m,String sdate, HttpServletResponse res) throws Exception {
		this.pw = res.getWriter();
		List<rainfall_dao> all = rm.ymdlist();
		
		rainfall_dao dao2 = null;
		
		if(sdate!=null) {	//do가 먼저 실행 -> ajax가 후발로 실행됨
			dao2 = rm.ajax_today(sdate);	//해당 일자 데이터 가져오는 형태
			//frontend에게 Storage로 전달하여 데이터를 출력할 수 있도록 데이터 가공
			this.pw.print(""
					+ "sessionStorage.setItem('area1','"+dao2.area_part1+"');"
					+ "sessionStorage.setItem('area2','"+dao2.area_part2+"');"
					+ "sessionStorage.setItem('area3','"+dao2.area_part3+"');"
					+ "sessionStorage.setItem('area4','"+dao2.area_part4+"');"
					+ "sessionStorage.setItem('area5','"+dao2.area_part5+"');");
			this.pw.close();
		}
		m.addAttribute("all", all);
		return "/rainfall/rainfall_list";	//view jsp 
	}
	
	
	
	//ajax로 같은 데이터가 입력되는 사항을 체크하는 메소드
	@GetMapping("/rainfall/rainfall_check.do")
	public String rainfall_check(
			@RequestParam(defaultValue = "", required = false) String today,
			HttpServletResponse res) throws Exception {
		try {
			System.out.println(today);
			if(!today.equals("")) {
				//selectOne 으로 처리된 module의 결과값을 받아서 Front-end에게 전달
				String checks = rm.ajax_select(today);
				this.pw = res.getWriter();
				this.pw.print(checks);	//0: 미등록된 일자, 1이상: 등록된 일자
			}
		}catch(Exception e) {
			this.pw.print("error");
		}finally {
			this.pw.close();
		}
		return null;
	}
	
	
	
	//통계데이터 관련 프로젝트 만들기(쓰기,출력,수정,삭제)
	@PostMapping("/rainfall/rainfall_writeok.do")
	public String rainfall_writeok(@ModelAttribute("fall") rainfall_dao dao,
			HttpServletResponse res) throws Exception {
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();
		try {
			int callback = rm.rain_insert(dao);
			if(callback>0) {
				this.pw.print("<script>"
						+ "alert('정상적으로 등록 완료되었습니다.');"
						+ "location.href='./rainfall_list.do';"
						+ "</script>");
			}
		}catch(Exception e) {
			System.out.println(e);
			this.pw.print("<script>"
					+ "alert('DB오류로 인하여 등록되지 않았습니다.');"
					+ "location.href='./rainfall_write.jsp';"
					+ "</script>");
		}finally {
			this.pw.close();
		}
		
		return null;
	}
	
	
	
	
	//데이터 한개의 값을 가져오는 페이지
	@GetMapping("/point_one.do")
	public String point_one(Model m) {
		int uidx = 15;	//인자값
		try {
			//selectOne으로 핸들링시 dao를 활용하여 데이터를 이관 받음
			pointdao result = pm.one_list(uidx);
			//샘플로 getter함수에 맞는 값으로 출력
			System.out.println(result.getUname());
			m.addAttribute("result", result);
		}catch(Exception e) {
			System.out.println(e);
			System.out.println("Model error!!");
		}
		return null;
	}
	
	//데이터 전체 리스트
	@GetMapping("/point_select.do")
	public String point_select(Model m) {
		try {
			List<pointdao> result = pm.all_list();
			m.addAttribute("result", result);
		}catch(Exception e) {
			System.out.println(e);
			System.out.println("Data Module error");
		}
		return null;
	}
}
