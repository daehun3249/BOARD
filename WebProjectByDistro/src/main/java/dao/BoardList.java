package dao;

import java.time.LocalDateTime;

public class BoardList {
	private String title;
	private String content;
	private String name;
	private LocalDateTime regDate;
	private int hits;
	private int postNumber;
	
	public BoardList(String title, String content, String name, LocalDateTime regDate, int hits, int postNumber) {
		this.title = title;
		this.content = content;
		this.name = name;
		this.regDate = regDate;
		this.hits = hits;
		this.postNumber = postNumber;
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
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public int getPostNumber() {
		return postNumber;
	}
	public void setPostNumber(int postNumber) {
		this.postNumber = postNumber;
	}
	
	
}
