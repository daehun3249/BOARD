package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {

	@RequestMapping("logout")
	public String logout(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		if(session.getAttribute("member") == null) {
			return "redirect:/";
		} else {
			session.invalidate();
			model.addAttribute("message", "로그아웃 되었습니다.");
			model.addAttribute("returnUrl", "/");
			return "alertAndRedirect";
		}
	}
}