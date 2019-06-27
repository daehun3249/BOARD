package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import dao.MemberDao;
import dao.WriteCommand;

@Controller
public class WriteBoardController {
	private MemberDao memberDao;
	
	public WriteBoardController(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@GetMapping("/write")
	public String writeForm(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("member") == null) {
			return "redirect:/";
		} else {
			return "board/write/writeForm";
		}
	}
	
	@PostMapping("/write")
	public String writeSuccess(WriteCommand command, HttpServletRequest request, Model model) {
		memberDao.write(command, request);
		model.addAttribute("message", "게시글이 등록되었습니다.");
		model.addAttribute("returnUrl", "/list");
		return "alertAndRedirect";
	}
}
