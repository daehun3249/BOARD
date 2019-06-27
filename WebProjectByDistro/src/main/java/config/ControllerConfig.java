package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import controller.BoardListController;
import controller.ChangePasswordController;
import controller.DeleteController;
import controller.LoginController;
import controller.LogoutController;
import controller.MemberJoinController;
import controller.ModifyController;
import controller.ReadController;
import controller.ReplyDeleteController;
import controller.ReplyModifyController;
import controller.ReplyWriteController;
import controller.WriteBoardController;
import dao.MemberDao;
import service.ChangePasswordService;
import service.MemberJoinService;

@Configuration
public class ControllerConfig {
	
	//SpringMvcConfig 에 있는 MemberDao 빈 등록
	@Autowired
	private MemberDao memberDao;
	
	//SpringMvcConfig 에 있는 Service 클래스들 등록
	@Autowired
	private MemberJoinService memberJoinService;
	
	@Autowired
	private ChangePasswordService changePasswordService;

	//Controller Bean 등록
	@Bean
	public MemberJoinController memberJoinController() {
		return new MemberJoinController(memberJoinService);
	}
	
	@Bean
	public LoginController loginController() {
		return new LoginController(memberDao);
	}
	
	@Bean
	public ChangePasswordController changePasswordController() {
		return new ChangePasswordController(changePasswordService);
	}
	
	@Bean
	public LogoutController logoutController() {
		return new LogoutController();
	}
	
	@Bean
	public BoardListController boardListController() {
		return new BoardListController(memberDao);
	}
	
	@Bean
	public WriteBoardController writeBoardController() {
		return new WriteBoardController(memberDao);
	}
	
	@Bean
	public ReadController readController() {
		return new ReadController(memberDao);
	}
	
	@Bean
	public ModifyController modifyController() {
		return new ModifyController(memberDao);
	}
	
	@Bean
	public DeleteController deleteController() {
		return new DeleteController(memberDao);
	}
	
	@Bean
	public ReplyWriteController replyWriteController() {
		return new ReplyWriteController(memberDao);
	}
	
	@Bean
	public ReplyModifyController replyModifyController() {
		return new ReplyModifyController(memberDao);
	}
	
	@Bean
	public ReplyDeleteController replyDeleteController() {
		return new ReplyDeleteController(memberDao);
	}
}
