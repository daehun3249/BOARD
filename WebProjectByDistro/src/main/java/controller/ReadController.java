package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import dao.Member;
import dao.MemberDao;
import dao.ReadContentCommand;
import dao.ReplyReadCommand;

@Controller
public class ReadController {
	private MemberDao memberDao;
	
	public ReadController(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	@GetMapping("/read/{no}")
	public String read(@PathVariable("no") int no, HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member");
		if(member == null) {
			model.addAttribute("message", "로그인이 필요한 서비스입니다.");
			model.addAttribute("returnUrl", "/login");
			return "alertAndRedirect";
		} else {
			ReadContentCommand content = memberDao.readByPostNumber(no);
			if(content == null) {
				model.addAttribute("message", "잘못된 접근입니다.");
				model.addAttribute("returnUrl", "/");
				return "alertAndRedirect";
			}
			List<ReplyReadCommand> readReply = memberDao.readReply(no);
			session.setAttribute("content", content);
			session.setAttribute("pageNo", no);
			session.setAttribute("readReply", readReply);
			return "board/read/read";
		}
	}

}