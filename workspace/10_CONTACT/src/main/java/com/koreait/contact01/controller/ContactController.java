package com.koreait.contact01.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.contact01.command.DeleteContactCommand;
import com.koreait.contact01.command.InsertContactCommand;
import com.koreait.contact01.command.SelectContactByNoCommand;
import com.koreait.contact01.command.SelectContactListCommand;
import com.koreait.contact01.command.UpdateContactCommand;
import com.koreait.contact01.dto.Contact;

@Controller
public class ContactController {

	// field
	private SelectContactListCommand selectContactListCommand;
	private InsertContactCommand insertContactCommand;
	private SelectContactByNoCommand selectContactByNoCommand;
	private UpdateContactCommand updateContactCommand;
	private DeleteContactCommand deleteContactCommand;
	
	// method
	@Autowired
	public void setCommand(SelectContactListCommand selectContactListCommand,
						   InsertContactCommand insertContactCommand,
						   SelectContactByNoCommand selectContactByNoCommand,
						   UpdateContactCommand updateContactCommand,
						   DeleteContactCommand deleteContactCommand) {
		this.selectContactListCommand = selectContactListCommand;
		this.insertContactCommand = insertContactCommand;
		this.selectContactByNoCommand = selectContactByNoCommand;
		this.updateContactCommand = updateContactCommand;
		this.deleteContactCommand = deleteContactCommand;
	}
	
	
	@GetMapping(value="/")
	public String index() {
		return "index";
	}
	
	
	@GetMapping(value="selectContactList.do")
	public String selectContactList(Model model) {
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
		insertContactCommand.execute(model);
	}
	
	
	@GetMapping(value="selectContactByNo.do")
	public String selectContactByNo(@RequestParam("no") long no,
									Model model) {
		model.addAttribute("no", no);
		selectContactByNoCommand.execute(model);
		return "contact/view";
	}
	
	
	@PostMapping(value="updateContact.do")
	public void updateContact(Contact contact,
								HttpServletResponse response,
								Model model) {
		model.addAttribute("contact", contact);
		model.addAttribute("response", response);
		updateContactCommand.execute(model);
	}
	
	@GetMapping(value="deleteContact.do")
	public void deleteContact(@RequestParam("no") long no,
							  HttpServletResponse response,
							  Model model) {
		model.addAttribute("no", no);
		model.addAttribute("response", response);
		deleteContactCommand.execute(model);		
	}
	
	
	
	
	
	
}
