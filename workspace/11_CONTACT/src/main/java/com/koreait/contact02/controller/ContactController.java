package com.koreait.contact02.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.contact02.command.DeleteContactCommand;
import com.koreait.contact02.command.InsertContactCommand;
import com.koreait.contact02.command.SelectContactByNoCommand;
import com.koreait.contact02.command.SelectContactListCommand;
import com.koreait.contact02.command.UpdateContactCommand;
import com.koreait.contact02.config.BeanConfiguration;
import com.koreait.contact02.dto.Contact;

@Controller
public class ContactController {

	private AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(BeanConfiguration.class);
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping(value="selectContactList.do")
	public String selectContactList(Model model) {
		SelectContactListCommand selectContactListCommand = ctx.getBean("selectContactListCommand", SelectContactListCommand.class);
		selectContactListCommand.execute(model);
		return "contact/list";
	}
	
	@GetMapping(value="insertContactPage.do")
	public String insertContactPage() {
		return "contact/insert";
	}
	
	@PostMapping(value="inserContact.do")
	public void inserContact(Contact contact,
							   HttpServletResponse response,
							   Model model) {
		model.addAttribute("contact", contact);
		model.addAttribute("response", response);
		InsertContactCommand insertContactCommand = ctx.getBean("insertContactCommand", InsertContactCommand.class);
		insertContactCommand.execute(model);
	}
	
	@GetMapping(value="selectContactByNo.do")
	public String selectContactByNo(@RequestParam("no") long no,
									Model model) {
		model.addAttribute("no", no);
		SelectContactByNoCommand selectContactByNoCommand = ctx.getBean("selectContactByNoCommand", SelectContactByNoCommand.class);
		selectContactByNoCommand.execute(model);
		return "contact/view";
	}
	
	@PostMapping(value="updateContact.do")
	public void updateContact(Contact contact,
								HttpServletResponse response,
								Model model) {
		model.addAttribute("contact", contact);
		model.addAttribute("response", response);
		UpdateContactCommand updateContactCommand = ctx.getBean("updateContactCommand", UpdateContactCommand.class);
		updateContactCommand.execute(model);
	}
	
	@GetMapping(value="deleteContact.do")
	public void deleteContact(@RequestParam("no") long no,
								HttpServletResponse response,
								Model model) {
		model.addAttribute("no", no);
		model.addAttribute("response", response);
		DeleteContactCommand deleteContactCommand = ctx.getBean("deleteContactCommand", DeleteContactCommand.class);
		deleteContactCommand.execute(model);
	}
	
	
	
	
	
	
	
	
	
	
}
