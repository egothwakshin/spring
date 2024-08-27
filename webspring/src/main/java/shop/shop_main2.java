package shop;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

//파일 업로드를 사용하는 Controller
@Controller
public class shop_main2 {
	
	/*
	  @PathVariable: REST API URI에 변수값을 이용허여 해당 페이지를 로드하는 형태의
	  데이터 출력방식. {}를 이용하여 id값을 핸들링
	  
	  API서버: REST,RESTful, CDN 해당 모든 사항이 포함됨
	  */
	@GetMapping("/api/{id}")
	public String apiurl(@PathVariable String id) {
		System.out.println(id);
		return null;
	}
	
	
	//ajax 동기화 및 비동기 테스트 페이지
	/*
	Spring ajax에 전송 데이터를 받을 경우 PrintWriter을 사용시 View를 생성할 필요 없음
	단, PrintWriter를 사용하지 않을 경우 무조건 view(.jsp)를 생성해야만 404에러를 방지할 수 있음 
	 */
	@PostMapping("/ajaxok.do")
	public String ajaxok(String mid, HttpServletResponse res) throws Exception {
		PrintWriter pw = res.getWriter();
		System.out.println(mid);
		Thread.sleep(5000);	//응답대기 시간을 설정할 수 있음
		pw.write("ok");
		pw.close();
		return null;
	}
	
	
	//jstl6.jsp 안에 있는 import 페이지에 해당 변수값 전달하기
	@GetMapping("/jstl6.do")
	public String jstls(Model m) {
		m.addAttribute("username","에이핑크♥♥♥♥♥♥♥♥");
		return null;
	}
	
	
	@Autowired
	BasicDataSource dbinfo;
	PrintWriter pw = null;
	
	public static int gidx;
	
	//예외처리 전용 어노테이션. 해당 Exception class를 이용하여 특정 페이지로 이동 또는 특정 페이지를 view 출력 
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public void han (MissingServletRequestParameterException ex, HttpServletResponse res) throws Exception{
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();
		this.pw.print("<script>"
				+ "alert('올바른 접근 방법이 아닙니다.');"
				+ "location.href='./gallery.do';"
				+ "</script>");
	}
	
	//갤러리 게시글 삭제
	@GetMapping("/gallery_del.do")
	public void gallery_del(@RequestParam(required = true) int gidx,
			HttpServletResponse res, HttpServletRequest req) throws Exception {
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();
		try {
			this.gidx = gidx;
			String callback = new gallery_delete().deleteok(dbinfo,req);
			if(callback=="Y") {
				this.pw.print("<script>"
						+ "alert('정상적으로 게시물이 삭제되었습니다.');"
						+ "location.href='./gallery.do';"
						+ "</script>");
				
			}else {
				this.pw.print("<script>"
						+ "alert('데이터 오류로 인하여 해당 게시물이 삭제되지 않았습니다.');"
						+ "location.href='./gallery.do';"
						+ "</script>");
			}			
		}catch(Exception e) {
			this.pw.print("<script>"
					+ "alert('올바른 접근 방법이 아닙니다.');"
					+ "location.href='./gallery.do';"
					+ "</script>");
		}finally {
			this.pw.close();
		}
		
		
		
		
		
		/*
		//내가 작성한 코드
		this.gidx = gidx;
		int result = new gallery_select().gallery_delete(dbinfo);
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();
		if(result>0) {
			this.pw.write("<script>"
					+ "alert('게시글 삭제가 완료되었습니다.');"
					+ "location.href='./gallery.do';"
					+ "</script>");
		}
		else {
			this.pw.write("<script>"
					+ "alert('데이터오류로 인하여 등록되지 않았습니다.');"
					+ "history.go(-1);"
					+ "</script>");
		}
		this.pw.close();
		*/
	}
	
	
	//갤러리 상세페이지
	@GetMapping("/gallery_view.do")
	public String gallery_view(Model m,@RequestParam(defaultValue="0", required=false) int gidx) {
		this.gidx = gidx;
		ArrayList<Object> one_list = new gallery_select().one_list(dbinfo);		
		m.addAttribute("one_list", one_list);
		
		return null;
	}
	
	
	//갤러리 게시판(리스트)
	@GetMapping("/gallery.do")
	public String gallery_list(Model m, gallery_dao dao) throws Exception {
		ArrayList<ArrayList<Object>> all_list = new gallery_select().all_list(dbinfo,dao);
		m.addAttribute("all_list",all_list);
		return null;		
	}

	
	
	//갤러리 게시판(글저장)
	//MultipartFile=> @RequestParam 사용시 form의 name만 적용
	@PostMapping("/galleryok.do")
	public void galleryok(@ModelAttribute("ga") gallery_dao dao,
			@RequestParam("files") MultipartFile files[],
			HttpServletResponse res, HttpServletRequest req) throws Exception{
		//배열기준으로 첫번째값만 확인하여 I/O 실행 유/무가 달라짐
		String result = new file_save().datafile_save(dbinfo, dao, files, req);
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();
		if(result=="Y") {
			this.pw.print("<script>"
					+ "alert('정상적으로 게시물이 등록되었습니다.');"
					+ "location.href='./gallery.do';"
					+ "</script>");
		}
		else {
			this.pw.print("<script>"
					+ "alert('데이터오류로 인하여 등록되지 않았습니다.');"
					+ "history.go(-1);"
					+ "</script>");
		}
		this.pw.close();
	}
	
	
	
	

	
	//동일한 첨부파일 name일 경우 원시배열로 값을 이관받음
	//Front에서 multiple을 구현하더라도 동일하게 원시배열로 이관받을 수 있음
	@PostMapping("fileupok2.do")
	public void fileupok2(@RequestParam("mfile") MultipartFile files[], HttpServletRequest req) throws Exception{
		int file_ea = files.length;	//Front에서 동일한 name을 몇개 사용했는지 보여줌
		System.out.println(file_ea);		
	}
	

	//@RequestParam("form에사용한 name명")
	@PostMapping("/fileupok.do")
	public void fileupok(@RequestParam("mfile") MultipartFile files, HttpServletRequest req) throws Exception {
		String filenm = files.getOriginalFilename();	//파일명
		long filesize = files.getSize();	//파일 사이즈
		String filetype = files.getContentType();	//파일 속성
		String url = req.getServletContext().getRealPath("/upload/");
		//System.out.println(url);

		if(filesize>2097152) {	//2MB
			System.out.println("첨부파일 용량은 2MB 이하여야 합니다.");
		}
		else {
			
		}
		
		File fe = new File(url+filenm);
		byte by[] = files.getBytes();
		FileCopyUtils.copy(by, fe);
	}
}
