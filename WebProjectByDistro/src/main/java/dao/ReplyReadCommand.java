package dao;

import java.time.LocalDateTime;

public class ReplyReadCommand {

	private int number;
	private String reply;
	private String name;
	private LocalDateTime regDate;
	
	public ReplyReadCommand(int number, String reply, String name, LocalDateTime regDate) {
		this.number = number;
		this.reply = reply;
		this.name = name;
		this.regDate = regDate;
	}
	
	
	public int getNumber() {
		return number;
	}


	public void setNumber(int number) {
		this.number = number;
	}


	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}
	
	
	
}
