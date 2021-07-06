package com.koreait.springproject.dto;

import java.sql.Date;

public class BoardReplyDTO {
	
	private long replyno;
	private String replywriter;
	private String replycontent;
	private Date replydate;
	private String replyip;
	
	public BoardReplyDTO() {
		super();
	}

	public BoardReplyDTO(long replyno, String replywriter, String replycontent, Date replydate, String replyip) {
		super();
		this.replyno = replyno;
		this.replywriter = replywriter;
		this.replycontent = replycontent;
		this.replydate = replydate;
		this.replyip = replyip;
	}

	public long getReplyno() {
		return replyno;
	}

	public void setReplyno(long replyno) {
		this.replyno = replyno;
	}

	public String getReplywriter() {
		return replywriter;
	}

	public void setReplywriter(String replywriter) {
		this.replywriter = replywriter;
	}

	public String getReplycontent() {
		return replycontent;
	}

	public void setReplycontent(String replycontent) {
		this.replycontent = replycontent;
	}

	public Date getReplydate() {
		return replydate;
	}

	public void setReplydate(Date replydate) {
		this.replydate = replydate;
	}

	public String getReplyip() {
		return replyip;
	}

	public void setReplyip(String replyip) {
		this.replyip = replyip;
	}
	
	
	
}
