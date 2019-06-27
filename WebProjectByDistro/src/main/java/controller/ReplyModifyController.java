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
import dao.ReplyModifyCommand;
import dao.ReplyReadCommand;
import exception.ReplyIdNotMatchException;

@Controller
public class ReplyModifyController {
	private MemberDao memberDao;
	
	public ReplyModifyController(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@GetMapping("/replyModify/{no}/{number}")
	public String replyModifyForm(@PathVariable("no") int no,@PathVariable("number") int number , HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member");
		if(member == null) {
			model.addAttribute("message", "잘못된 접근입니다.");
			model.addAttribute("returnUrl", "/");
			return "alertAndRedirect";
		}
		ReplyReadCommand command = memberDao.readReplyForDeleteReply(no, number);
		if(command == null) {
			model.addAttribute("message", "잘못된 접근입니다.");
			model.addAttribute("returnUrl", "/");
			return "alertAndRedirect";
		} else {
			session.setAttribute("no", no);
			session.setAttribute("number", number);
			return "board/modify/replyModifyForm";
		}
	}
	
	@PostMapping("/replyModify/{no}/{number}")
	public String replyModifySuccess(@PathVariable("no") int no,@PathVariable("number") int number, ReplyModifyCommand command, HttpServletRequest request, Model model) {
		try {
			memberDao.modifyReply(no, number, request, command);
			model.addAttribute("message", "댓글이 수정되었습니다.");
			model.addAttribute("returnUrl", "/read/" + no);
			return "alertAndRedirect";
		} catch(ReplyIdNotMatchException e) {
			model.addAttribute("message", "본인 댓글만 수정할 수 있습니다.");
			model.addAttribute("returnUrl", "/read/" + no);
			return "alertAndRedirect";
		}
	}
}