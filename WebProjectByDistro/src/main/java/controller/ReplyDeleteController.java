package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import dao.Member;
import dao.MemberDao;
import dao.ReplyReadCommand;
import exception.ReplyIdNotMatchException;

@Controller
public class ReplyDeleteController {
	private MemberDao memberDao;
	
	public ReplyDeleteController(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@GetMapping("/replyDelete/{no}/{number}")
	public String replyDelete(@PathVariable("no") int no, @PathVariable("number") int number, HttpServletRequest request, Model model) {
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
			model.addAttribute("message", "삭제하시겠습니까?");
			model.addAttribute("returnUrl", "/replyDeleteSuccess/" + no + "/" + number);
			return "yesOrNo";
		}
	}
	
	@GetMapping("/replyDeleteSuccess/{no}/{number}")
	public String replyDeleteSuccess(@PathVariable("no") int no, @PathVariable("number") int number, HttpServletRequest request, Model model) {
		try {
			memberDao.DeleteReply(no, number, request);
			model.addAttribute("message", "댓글이 삭제되었습니다.");
			model.addAttribute("returnUrl", "/read/" + no);
			return "alertAndRedirect";
		} catch(ReplyIdNotMatchException e) {
			model.addAttribute("message", "본인의 댓글만 삭제할 수 있습니다.");
			model.addAttribute("returnUrl", "/read/" + no);
			return "alertAndRedirect";
		}
		
	}
}
