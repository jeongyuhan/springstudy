package com.koreait.springproject.dto;

import java.sql.Date;

public class MemberDTO {
	
	private long no;
	private String id;
	private String pw;
	private String name;
	private String phone;
	private String email;
	private String address;
	private Date regdate;
	private int state;

	public MemberDTO() {
		super();
	}

	public MemberDTO(long no, String id, String pw, String name, String phone, String email, String address,
			Date regdate, int state) {
		super();
		this.no = no;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.regdate = regdate;
		this.state = state;
	}

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	
	
}
