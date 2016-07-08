package com.atguigu.crm.handler;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.crm.entity.Contact;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.ContactService;
@RequestMapping(value="/contact")
@Controller
public class ContactHandler {
	private ContactService contactService;
	
	@RequestMapping("/list/{id}")
	public String list(@PathVariable("id") Integer id,Map<String,Object>map){
		Page<Contact>page=contactService.getContact(id);
		map.put("page", page);
		return "contact/list";
	}
	
	
}
