package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import dao.BoardList;
import dao.MemberDao;

@Controller
public class BoardListController {
	private MemberDao memberDao;
	
	public BoardListController(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@GetMapping("/list")
	public String list(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		if(session.getAttribute("member") == null) {
			model.addAttribute("message", "로그인이 필요한 서비스입니다.");
			model.addAttribute("returnUrl", "/login");
			return "alertAndRedirect";
		} else {
			List<BoardList> boardList = memberDao.selectBoardList();
			session.setAttribute("boardList", boardList);
			return "board/list/list";
		}
	}
}
