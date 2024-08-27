package webspring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class LoginController {
	
	@PostMapping("/test/login")
	public String login(@RequestBody LoginDto logindto) {
		System.out.println(logindto.getUserid());
		return null;
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "/ecma/ecma9.jsp";
	}
}
