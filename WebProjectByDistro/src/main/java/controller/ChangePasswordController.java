package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import dao.ChangePasswordCommand;
import exception.InvalidPasswordException;
import service.ChangePasswordService;

@Controller
public class ChangePasswordController {

	private ChangePasswordService changePasswordService;
	
	public ChangePasswordController(ChangePasswordService changePasswordService) {
		this.changePasswordService = changePasswordService;
	}
	
	@GetMapping("/change")
	public String changePasswordForm(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		if(session.getAttribute("member") == null) {
			model.addAttribute("message", "로그인이 필요한 서비스입니다.");
			model.addAttribute("returnUrl", "/login");
			return "alertAndRedirect";
		} else {
			return "changePassword/changePasswordForm";
		}
	}
	
	@PostMapping("/change")
	public String changeSuccess(ChangePasswordCommand command, HttpServletRequest request, Model model) {
		try {
			changePasswordService.changePassword(command, request);
			HttpSession session = request.getSession();
			session.invalidate();
			model.addAttribute("message", "암호가 변경되었습니다 다시 로그인해주세요.");
			model.addAttribute("returnUrl", "/login");
			return "alertAndRedirect";
		} catch(InvalidPasswordException e) {
			model.addAttribute("message", "이전 암호가 올바르지 않습니다.");
			model.addAttribute("returnUrl", "/change");
			return "alertAndRedirect";
		}
	}
}
