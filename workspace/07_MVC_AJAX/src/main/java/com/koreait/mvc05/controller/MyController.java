package com.koreait.mvc05.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.mvc05.dto.Person;

@Controller
public class MyController {
	
	@RequestMapping("/")
	public String contextPath() {
		return "index";
	}
	
	//1. String 반환하기
	@RequestMapping(value="v01", 
					method=RequestMethod.GET,
					produces="text/plain; charset=utf-8")
	@ResponseBody // @ResponseBody가 없으면 return되는 값이 뷰로 인식되기 때문에 값으로 반환하기 위해선 반드시 붙여주어야한다.
	public String v01(@RequestParam("name") String name,
					  @RequestParam("age") int age) {
		return name + ", " + age; // @ResponseBody로 인해 name + ", " + age 부분이 ViewResolver에서 jsp가 아닌 값으로 인식되어 반환된다.
	}
	
	
	/*
	@RequestMapping(value="v01", 
					method=RequestMethod.GET,
					produces="application/json; charset=utf-8")
	@ResponseBody // ajax 처리를 위한 필수 애너테이션
	public Person v01(@RequestParam("name") String name, @RequestParam("age") int age) {
		Person p = new Person();
		p.setName(name);
		p.setAge(age);
		return p; // bean을 반환한다. produces="application/json; charset=utf-8"을 통해서 bean은 json으로 변경이 된다. jackson에 의해서!
				  // return은 ViewResolver에 의해서 jsp로 처리가 되는데 이를 방지하기 위해서 return이 "값"임을 알린다. @ResponseBody 애너테이션에 의해서!
	}				
	*/
	
	// 2. json 반환
	@RequestMapping(value="v02", 
					method=RequestMethod.GET,
					produces="application/json; charset=utf-8")
	@ResponseBody // ajax 처리를 위한 필수 애너테이션
	public Person v02(@RequestParam("name") String name, 
					  @RequestParam("age") int age) {
	Person p = new Person();
	p.setName(name);
	p.setAge(age);
	return p; // bean을 반환한다. produces="application/json; charset=utf-8"을 통해서 bean은 json으로 변경이 된다. jackson에 의해서!
			  // return은 ViewResolver에 의해서 jsp로 처리가 되는데 이를 방지하기 위해서 return이 "값"임을 알린다. @ResponseBody 애너테이션에 의해서!
	}				
	
	
	// 3. json 받아서 json 반환하기
	@RequestMapping(value="v03",
					method=RequestMethod.POST,
					produces="application/json; charset=utf-8")
	@ResponseBody
	public Person v03(@RequestBody Person person) {
		System.out.println(person);
		return person;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
