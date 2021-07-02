package com.koreait.springproject.dto;

import java.sql.Date;

public class BoardDTO {
	
	private long bno;
	private String writer;
	private String title;
	private String content;
	private Date postdate; 
	private Date lastmodified;
	private String ip;
	private int hit;
	private String image;
	
	public BoardDTO() {
		super();
	}

	public BoardDTO(long bno, String writer, String title, String content, Date postdate, Date lastmodified, String ip,
			int hit, String image) {
		super();
		this.bno = bno;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.postdate = postdate;
		this.lastmodified = lastmodified;
		this.ip = ip;
		this.hit = hit;
		this.image = image;
	}

	public long getBno() {
		return bno;
	}

	public void setBno(long bno) {
		this.bno = bno;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
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

	public Date getPostdate() {
		return postdate;
	}

	public void setPostdate(Date postdate) {
		this.postdate = postdate;
	}

	public Date getLastmodified() {
		return lastmodified;
	}

	public void setLastmodified(Date lastmodified) {
		this.lastmodified = lastmodified;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
	
	
	
}
