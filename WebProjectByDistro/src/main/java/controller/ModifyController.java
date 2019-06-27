package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import dao.Member;
import dao.MemberDao;
import dao.ReadContentCommand;
import exception.BoardIdNotMatchException;

@Controller
public class ModifyController {
	private MemberDao memberDao;
	
	public ModifyController(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	@GetMapping("/modify/{no}")
	public String modifyForm(@PathVariable("no") int no, HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member");
		ReadContentCommand content = memberDao.modifyBefore(no);
		if(member == null) {
			model.addAttribute("message", "잘못된 접근입니다.");
			model.addAttribute("returnUrl", "/");
			return "alertAndRedirect";
		}
		if(content == null) {
			model.addAttribute("message", "잘못된 접근입니다.");
			model.addAttribute("returnUrl", "/");
			return "alertAndRedirect";
		}
		request.setAttribute("content", content);
		request.setAttribute("no", no);
		return "board/modify/modifyForm";
	}
	
	@PostMapping("/modify/{no}")
	public String modifySuccess(@PathVariable int no, ReadContentCommand content, HttpServletRequest request, Model model) {
		try {
			memberDao.modifyAfter(content, no, request);
			model.addAttribute("message", "게시글이 수정되었습니다.");
			model.addAttribute("returnUrl", "/read/" + no);
			return "alertAndRedirect";
		} catch(BoardIdNotMatchException e) {
			model.addAttribute("message", "본인 게시글만 수정할 수 있습니다.");
			model.addAttribute("returnUrl", "/read/" + no);
			return "alertAndRedirect";
		}
	}
}
