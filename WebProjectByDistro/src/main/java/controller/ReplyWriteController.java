package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import dao.MemberDao;
import dao.ReplyWriteCommand;

@Controller
public class ReplyWriteController {

	private MemberDao memberDao;
	
	public ReplyWriteController(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	@PostMapping("/read/{no}")
	public String replyWrite(@PathVariable("no") int no, ReplyWriteCommand command) {
		memberDao.writeReply(command);
		return "redirect:/read/{no}";
	}
}
