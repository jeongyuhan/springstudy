package com.koreait.contact03.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.contact03.command.DeleteContactCommand;
import com.koreait.contact03.command.InsertContactCommand;
import com.koreait.contact03.command.SelectContactByNoCommand;
import com.koreait.contact03.command.SelectContactListCommand;
import com.koreait.contact03.command.UpdateContactCommand;
import com.koreait.contact03.dto.Contact;

@Controller
public class ContactController {

	// field
	private SqlSession sqlSession;
	private SelectContactListCommand selectContactListCommand;
	private InsertContactCommand insertContactCommand;
	private SelectContactByNoCommand selectContactByNoCommand;
	private UpdateContactCommand updateContactCommand;
	private DeleteContactCommand deleteContactCommand;
	
	
	
	@Autowired
	public ContactController(SqlSession sqlSession, 
							 SelectContactListCommand selectContactListCommand,
							 InsertContactCommand insertContactCommand, 
							 SelectContactByNoCommand selectContactByNoCommand,
							 UpdateContactCommand updateContactCommand, 
							 DeleteContactCommand deleteContactCommand) {
		super();
		this.sqlSession = sqlSession;
		this.selectContactListCommand = selectContactListCommand;
		this.insertContactCommand = insertContactCommand;
		this.selectContactByNoCommand = selectContactByNoCommand;
		this.updateContactCommand = updateContactCommand;
		this.deleteContactCommand = deleteContactCommand;
	}

	// method
	@GetMapping(value= {"/", "selectContactList.do"})
	public String selectContactList(Model model) {
		selectContactListCommand.execute(sqlSession, model);
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
		insertContactCommand.execute(sqlSession, model);
	}
	
	
	@GetMapping(value="selectContactByNo.do")
	public String selectContactByNo(@RequestParam("no") long no,
									Model model) {
		model.addAttribute("no", no);
		selectContactByNoCommand.execute(sqlSession, model);
		return "contact/view";
	}
	
	
	@PostMapping(value="updateContact.do")
	public void updateContact(Contact contact,
								HttpServletResponse response,
								Model model) {
		model.addAttribute("contact", contact);
		model.addAttribute("response", response);
		updateContactCommand.execute(sqlSession, model);
	}
	
	
	@GetMapping(value="deleteContact.do")
	public void deleteContact(@RequestParam("no") long no,
								HttpServletResponse response,
								Model model) {
		model.addAttribute("no", no);
		model.addAttribute("response", response);
		deleteContactCommand.execute(sqlSession, model);
	}

}
