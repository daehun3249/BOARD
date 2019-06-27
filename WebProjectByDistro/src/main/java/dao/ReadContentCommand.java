package dao;

import java.time.LocalDateTime;

public class ReadContentCommand {
	private String title;
	private String content;
	private String name;
	private LocalDateTime regDate;
	
	public ReadContentCommand(String title, String content, String name, LocalDateTime regDate) {
		this.title = title;
		this.content = content;
		this.name = name;
		this.regDate = regDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
