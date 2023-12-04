package com.studies.controllers;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.studies.model.Contact;

@Controller
public class ContactsController {

	private static final ArrayList<Contact> LISTA_CONTATOS = new ArrayList<>();

	static {
		LISTA_CONTATOS.add(new Contact("1", "Maria", "+55 34 00000 0000"));
		LISTA_CONTATOS.add(new Contact("2", "João", "+55 34 00000 0000"));
		LISTA_CONTATOS.add(new Contact("3", "Normandes", "+55 34 00000 0000"));
		LISTA_CONTATOS.add(new Contact("4", "Thiago", "+55 34 00000 0000"));
		LISTA_CONTATOS.add(new Contact("5", "Alexandre", "+55 34 00000 0000"));
	}

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/contacts")
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("list");

		modelAndView.addObject("contacts", LISTA_CONTATOS);

		return modelAndView;
	}

	@GetMapping("/contacts/new")
	public ModelAndView newContact() {
		ModelAndView modelAndView = new ModelAndView("form");

		modelAndView.addObject("contact", new Contact());
		return modelAndView;
	}
	
	@PostMapping("/contacts")
	public String register(Contact contact) {
		String id = UUID.randomUUID().toString();
		contact.setId(id);
		LISTA_CONTATOS.add(contact);
		
		
		return "redirect:/contacts";
	}
	
	
	
	
	
	
	
	
	
}
