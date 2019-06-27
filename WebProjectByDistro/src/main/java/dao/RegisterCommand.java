package dao;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class RegisterCommand {
	
	@NotEmpty(message = "필수 입력값 입니다.")
	private String id;
	@Size(min = 6, message = "최소 6자리 이상 입력하십시오.")
	@NotEmpty(message = "필수 입력값 입니다.")
	private String password;
	@NotEmpty(message = "필수 입력값 입니다.")
	private String confirmPassword;
	@Email(message = "이메일 형식이 올바르지 않습니다.")
	@NotEmpty(message = "필수 입력값 입니다.")
	private String email;
	@NotEmpty(message = "필수 입력값 입니다.")
	private String name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean matchPassword() {
		return this.password.equals(confirmPassword);
	}
}
