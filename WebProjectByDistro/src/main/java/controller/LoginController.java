package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import dao.LoginCommand;
import dao.Member;
import dao.MemberDao;

@Controller
public class LoginController {
	private MemberDao memberDao;
	
	public LoginController(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@GetMapping("/login")
	public String loginForm() {
		return "login/loginForm";
	}
	
	@PostMapping("/login")
	public String loginSuccess(LoginCommand command, HttpServletRequest request, HttpServletResponse response, Model model) {
		Member member = memberDao.login(command);
		if(member == null) {
			model.addAttribute("message", "아이디 혹은 비밀번호가 일치하지 않습니다.");
			model.addAttribute("returnUrl", "/login");
			return "alertAndRedirect";
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("member", member);
			model.addAttribute("message", "로그인에 성공하였습니다.");
			model.addAttribute("returnUrl", "/");
			return "alertAndRedirect";
		}
	}
}