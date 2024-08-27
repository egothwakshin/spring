package api;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class notice_controller {
	PrintWriter pw = null;
	
	@Resource(name="notice")
	private notice_module nm;
	
	//선생님코드 게시판삭제
	@GetMapping("/notice/notice_delete.do")
	public String notice_delete(String nidx) {
		try {
			int result = nm.del_notice(nidx);
		
		}catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	/*	
	//내코드 게시판삭제
	@PostMapping("/notice/notice_delete.do")
	public String notice_delete(@RequestParam String ck[]) {
		System.out.println(ck.length);
		//this.nm.select_del(ck);
		return null;
	}
	*/
	
	@GetMapping("/notice/notice_list.do")
	public String notice_list(Model m,
			@RequestParam(defaultValue = "", required = false) String search_part,
			@RequestParam(defaultValue = "", required = false) String search_word) {
		List<notice_dao> result = null;
		try {
			if(search_part.equals("") && search_word.equals("")) {
				result = nm.alldata();
			}
			else {
				m.addAttribute("search_part", search_part);
				m.addAttribute("search_word", search_word);
				result = this.nm.alldata(search_part,search_word);				
			}
			m.addAttribute("result", result);
			
		}catch(Exception e) {
			System.out.println(e);
			System.out.println("DB Error or Query Error");
		}
		
		
		return null;
	}
}
