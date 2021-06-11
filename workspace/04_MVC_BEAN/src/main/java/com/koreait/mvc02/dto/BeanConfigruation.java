package com.koreait.mvc02.dto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfigruation {

	@Bean
	public Contact contact3() {
		return new Contact("010-3333-3333", "서울시 용산구");
	}
	
	@Bean
	public Member member3() {
		return new Member("alice", contact3());
	}
	
}
