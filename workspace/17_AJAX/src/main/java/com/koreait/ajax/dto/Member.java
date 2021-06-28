package com.koreait.ajax.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // Member(){}
@AllArgsConstructor // Member(필드){this.필드}
@Data // getter/setter
public class Member {
	
	private long no;
	private String id;
	private String name;
	private String address;
	private String gender;
	
}
