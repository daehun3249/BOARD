package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import dao.Member;
import dao.MemberDao;
import dao.ReadContentCommand;
import exception.BoardIdNotMatchException;

@Controller
public class DeleteController {
	private MemberDao memberDao;
	
	public DeleteController(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@GetMapping("/delete/{no}")
	public String delete(@PathVariable int no, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("member");
		if(member == null) {
			model.addAttribute("message", "잘못된 접근입니다.");
			model.addAttribute("returnUrl", "/");
			return "alertAndRedirect";
		}
		ReadContentCommand command = memberDao.readForDelete(no);
		if(command == null) {
			model.addAttribute("message", "잘못된 접근입니다.");
			model.addAttribute("returnUrl", "/");
			return "alertAndRedirect";
		} else {
			model.addAttribute("message", "삭제하시겠습니까?");
			model.addAttribute("returnUrl", "/deleteSuccess/" + no);
			return "yesOrNo";
		}
	}
	
	@GetMapping("/deleteSuccess/{no}")
	public String deleteSuccess(@PathVariable int no, Model model, HttpServletRequest request) {
		try {
			memberDao.delete(no, request);
			model.addAttribute("message", "게시물 삭제에 성공하였습니다.");
			model.addAttribute("returnUrl", "/list");
			return "alertAndRedirect";
		} catch(BoardIdNotMatchException e) {
			model.addAttribute("message", "본인 게시글만 삭제할 수 있습니다.");
			model.addAttribute("returnUrl", "/read/" + no);
			return "alertAndRedirect";
		}
	}
}
