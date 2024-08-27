package webspring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


//Controller
public class test implements Controller {
	
	
	
	//POST,GET 모두 사용가능한 핸들러
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//MAP + Attribute를 보다 쉽게 사용할 수 있도록 변환된 클래스임 (Spring전용)
		ModelAndView mv = new ModelAndView();
		String search = request.getParameter("search");
		System.out.println(search);
		
		String username = "홍길동";
		int age = 33;
		//해당 변수값 및 key 이름으로 배열 형태의 구성으로 추가됨
		mv.addObject("username", username);
		mv.addObject("userage", age);
		mv.setViewName("test.jsp");		
		return mv;
	}
}
