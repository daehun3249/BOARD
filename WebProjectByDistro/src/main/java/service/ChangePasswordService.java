package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.ChangePasswordCommand;
import dao.Member;
import dao.MemberDao;
import exception.InvalidPasswordException;

public class ChangePasswordService {

	private MemberDao memberDao;
	
	public ChangePasswordService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public void changePassword(ChangePasswordCommand command, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member");
		if(command.getOldPassword().equals(member.getPassword())) {
			memberDao.update(command, request);
		} else {
			throw new InvalidPasswordException();
		}
	}
}
