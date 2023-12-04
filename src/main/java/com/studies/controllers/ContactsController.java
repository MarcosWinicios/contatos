package com.studies.controllers;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@GetMapping("contacts/{id}/editar")
	public ModelAndView edit(@PathVariable String id) {
		ModelAndView modelAndView = new ModelAndView("form");

		Contact contact = findContact(id);

		modelAndView.addObject("contact", contact);
		return modelAndView;
	}

	@PutMapping("/contacts/{id}")
	public String update(Contact contact) {
		Integer index = findContactIndex(contact.getId());

		Contact oldContact = LISTA_CONTATOS.get(index);
		LISTA_CONTATOS.remove(oldContact);
		LISTA_CONTATOS.add(index, contact);

		return "redirect:/contacts";
	}

	// ----------------------------- helper methods

	private Contact findContact(String id) {
		Integer index = findContactIndex(id);

		if (index != null) {
			Contact contact = LISTA_CONTATOS.get(index);
			return contact;
		}

		return null;
	}

	private Integer findContactIndex(String id) {
		for (int i = 0; i < LISTA_CONTATOS.size(); i++) {
			Contact contact = LISTA_CONTATOS.get(i);

			if (contact.getId().equals(id)) {
				return i;
			}
		}

		return null;
	}

}
