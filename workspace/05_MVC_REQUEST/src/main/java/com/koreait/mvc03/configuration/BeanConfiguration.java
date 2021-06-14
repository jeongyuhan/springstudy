package com.koreait.mvc03.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.koreait.mvc03.dto.Person;

@Configuration
public class BeanConfiguration {
	
	@Bean
	public Person man() {
		Person m = new Person();
		m.setName("데이빗");
		m.setAge(24);
		List<String> hobbies = new ArrayList<String>();
		hobbies.add("헬스");
		hobbies.add("영화");
		m.setHobbies(hobbies);
		
		return m;
	}
	
	@Bean
	public Person woman() {
		Person w = new Person();
		w.setName("엘리스");
		w.setAge(28);
		List<String> hobbies = new ArrayList<String>();
		hobbies.add("요가");
		hobbies.add("카페투어");
		w.setHobbies(hobbies);
		
		return w;
	}
	
}
