package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import dao.RegisterCommand;
import exception.DuplicateIdException;
import exception.PasswordNotMatchException;
import service.MemberJoinService;

@Controller
public class MemberJoinController {
	
	private MemberJoinService memberJoinService;
	
	public MemberJoinController(MemberJoinService memberJoinService) {
		this.memberJoinService = memberJoinService;
	}

	@GetMapping("/join")
	public String joinMember() {
		return "join/joinForm";
	}
	
	@PostMapping("/join")
	public String joinSuccess(RegisterCommand command, Model model) {
		try {
			memberJoinService.joinService(command);
		} catch(DuplicateIdException e) {
			model.addAttribute("message", "중복된 아이디입니다. 다른 아이디를 입력해주세요.");
			model.addAttribute("returnUrl", "/join");
			return "alertAndRedirect";
		} catch(PasswordNotMatchException e) {
			model.addAttribute("message", "패스워드가 일치하지 않습니다.");
			model.addAttribute("returnUrl", "/join");
			return "alertAndRedirect";
		}
		model.addAttribute("message", "회원가입에 성공하였습니다. 로그인해주세요.");
		model.addAttribute("returnUrl", "/login");
		return "alertAndRedirect";
	}
}
