package com.studies.controllers;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

import com.studies.model.Contact;

@Controller
public class ContactsController {

	private static final ArrayList<Contact> CONTACT_LIST = new ArrayList<>();

	static {
		CONTACT_LIST.add(new Contact("1", "Maria", "+55 34 00000 0000"));
		CONTACT_LIST.add(new Contact("2", "João", "+55 34 00000 0000"));
		CONTACT_LIST.add(new Contact("3", "Normandes", "+55 34 00000 0000"));
		CONTACT_LIST.add(new Contact("4", "Thiago", "+55 34 00000 0000"));
		CONTACT_LIST.add(new Contact("5", "Alexandre", "+55 34 00000 0000"));
	}

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/contacts")
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("list");

		modelAndView.addObject("contacts", CONTACT_LIST);

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
		CONTACT_LIST.add(contact);

		return "redirect:/contacts";
	}

	@GetMapping("contacts/{id}/edit")
	public ModelAndView edit(@PathVariable String id) {
		ModelAndView modelAndView = new ModelAndView("form");

		Contact contact = findContact(id);

		modelAndView.addObject("contact", contact);
		return modelAndView;
	}

	@PutMapping("/contacts/{id}")
	public String update(Contact contact) {
		Integer index = findContactIndex(contact.getId());

		Contact oldContact = CONTACT_LIST.get(index);
		CONTACT_LIST.remove(oldContact);
		CONTACT_LIST.add(index, contact);

		return "redirect:/contacts";
	}
	
	@DeleteMapping("/contacts/{id}")
	public String delete(@PathVariable String id) {
		Contact contact = findContact(id);
		CONTACT_LIST.remove(contact);
		
		return "redirect:/contacts";
	}

	// ----------------------------- helper methods

	private Contact findContact(String id) {
		Integer index = findContactIndex(id);

		if (index != null) {
			Contact contact = CONTACT_LIST.get(index);
			return contact;
		}

		return null;
	}

	private Integer findContactIndex(String id) {
		for (int i = 0; i < CONTACT_LIST.size(); i++) {
			Contact contact = CONTACT_LIST.get(i);

			if (contact.getId().equals(id)) {
				return i;
			}
		}

		return null;
	}

}
