package service;

import dao.Member;
import dao.MemberDao;
import dao.RegisterCommand;
import exception.DuplicateIdException;
import exception.PasswordNotMatchException;

public class MemberJoinService {
	private MemberDao memberDao;
	
	public MemberJoinService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public void joinService(RegisterCommand command) {
		Member memberIdData = memberDao.selectById(command.getId());
		if(memberIdData != null) {
			throw new DuplicateIdException();
		}
		if(!command.matchPassword()) {
			throw new PasswordNotMatchException();
		}
		memberDao.join(command);
	}
}