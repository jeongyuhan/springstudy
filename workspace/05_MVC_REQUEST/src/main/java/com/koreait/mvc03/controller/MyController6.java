package com.koreait.mvc03.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.mvc03.dto.Person;

@Controller
public class MyController6 {
	
	/*
		전달 받은 파라미터 처리
		
		1. HttpServletRequest request 사용
		2. @RequestParam
		 -@RequestParam의 Null처리
		3. DTO 
		4. @ModelAttribute
	*/
	
	// 1. request 이용하여 파라미터 처리
	@RequestMapping("f5/v01")
	public String a(HttpServletRequest request, Model model) { // request에 name, age 파라미터
		// request는 index.jsp에서 받아온 요청을 처리하고, model은 결과가 나오는 응답을 처리한다.
		// request로 요청 처리
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		// model로 응답 처리
		model.addAttribute("name", name); // request에 name 속성
		model.addAttribute("age", age);   // request에 age 속성
		
		return "folder05/view01"; // forward : 기존 request를 보내준다.
	}
	
	// 2. @RequestParam를 이용하여 파라미터 처리
	// @RequestParam(value="파라미터")
	// @RequestParam("파라미터") // value속성만 사용하는 경우 value=는 생략가능하다.
	@RequestMapping("f5/v02")
	public String b(@RequestParam("name") String name, // @RequestParam(파라미터명) 저장할 변수, String name = request.getparameter("name"); 을 대체하는 코드
					@RequestParam("age") int age,
					Model model) {
		
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		
		return "folder05/view02";
	}
	
	// - @RequestParam의 Null처리
	// @RequestParam(value="파라미터", required=ture(파라미터가 반드시 존재한다.)/false(파라미터가 없어도 처리된다.))
	@RequestMapping("f5/v03")
	public String c(@RequestParam(value="name", required=false) String name,
					@RequestParam(value="age", required=false, defaultValue="0") int age, // name의 경우 문자열이기 때문에 null이 가능하지만 
																						  // age의 경우 int이기 때문에 자동으로 parsing되면서 오류가 발생한다.
																						  // defaultValue="0"을 통해 기본값을 0으로 준다.
					Model model) {
		
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		
		return "folder05/view03";
	}
	
	// 3. DTO 이용하기
	// DTO를 생성해두면 DTO가 알아서 파라미터를 받는다.
	// setName(), setAge()를 찾아서 알아서 들어가서 저장되는 것이다.
	@RequestMapping("f5/v04")
	public String d(Person person, // 파라미터 name, age가 person에 저장된다.
					Model model) {
		
		model.addAttribute("person", person);
		
		return "folder05/view04";
	}
	
	// 4. @ModelAttribute
	@RequestMapping("f5/v05")
	public String e(@ModelAttribute(value="name") String name, // 파라미터 name을 String name에 저장한 뒤 model에 저장한다.
					@ModelAttribute("age") int age) {
		return "folder05/view05";
	}
	
	
	// 5. @ModelAttribute
	// DTO를 이용
	@RequestMapping("f5/v06")
	public String f(@ModelAttribute("person") Person person) {
		return "folder05/view06";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
