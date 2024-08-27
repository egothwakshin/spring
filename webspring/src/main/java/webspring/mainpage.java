package webspring;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class mainpage {
	@Autowired
	HttpServletResponse response;
	//※ 즉시실행 메소드는 Spring에서 사용하지 않음(Controller)

	//가상의 웹 페이지1
	//@RequestMapping("/mainpage.do")	//POST,GET 둘다 가능
	@PostMapping("/mainpage.do")		//POST
	//@GetMapping("/mainpage.do")			//GET
	//response을 사용시 JSTL을 사용하지 못함. 일반 JSP를 사용해야함(Model 사용못함)
	//PrintWriter 사용시 response를 사용하며 해당 script를 활성화시킴. 단 view페이지는 만들지 않음.
	
	
	public String main(HttpServletRequest req, Model m) {
		String search = req.getParameter("search");
		System.out.println(search);
		m.addAttribute("search",search);
		return null;	//.jsp를 입력하지않아도 됨. (webpage.xml의 prefix,suffix 적용되었으므로)
	}
	
	//기본적으로 spring Controller=View(.jsp)
	//가상의 웹 페이지2 (void) - jsp를 사용하지 않을 경우 Script를 이용함
	@RequestMapping("/mainpage2.do")
	public void main2(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String mid = req.getParameter("mid");
		String mpass = req.getParameter("mpass");
		//암기★HttpServletResponse 사용하는경우 => PrintWriter
		res.setContentType("text/html;charset=utf-8");
		PrintWriter pw = res.getWriter();
		
		if(mid.equals("")) {
			System.out.println("아이디 빈값");
		}
		else {
			pw.write("<script>"
					+ "alert('로그인 되셨습니다.');"
					+ "location.href='./test.jsp';"
					+ "</script>");	
		}
		pw.close();
	}
	/*
	일반 자료형에서 return null 일 경우 Mapping주소와 같은 jsp 파일을 찾습니다.
	ModelAndView같은 특정자료형에서 return null 일 경우 jsp 찾지 않음.
	*/
	 
	ModelAndView mv = new ModelAndView();
	//가상의 웹 페이지3
	@RequestMapping("/mainpage3.do")
	public ModelAndView main3(HttpServletRequest req) {
		String mid = req.getParameter("mid");
		String mpass = req.getParameter("mpass");
		
		this.mv.addObject("mid", mid);
		this.mv.setView(null);	//null은 Mapping이름과 동일한 jsp를 찾게 됩니다.
		return mv;
	}
	
	//가상의 웹 페이지4 (int,Integer,float,long 안됨)
	//작동가능: void, String, StringBuffer, ModelanView
	//배열클래스도 작동가능 (ArrayList, List, Map, LinkedList)
	//ModelandView는 메소드 자료형이 ModelandView이어야만 사용가능 
	@RequestMapping("/mainpage4.do")
	public ArrayList<Integer> main4(HttpServletRequest req, Model m) {
		String mid = req.getParameter("mid");
		String mpass = req.getParameter("mpass");
		ArrayList<Integer> al = new ArrayList<Integer>();
		al.add(25);
		al.add(35);
		al.add(45);
		m.addAttribute("mid",mid);
		m.addAttribute("al",al);
		return null;
	}
	
	
}
