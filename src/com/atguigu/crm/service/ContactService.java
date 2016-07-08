package com.atguigu.crm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.atguigu.crm.entity.Contact;
import com.atguigu.crm.mapper.ContactMapper;
import com.atguigu.crm.orm.Page;

@Service
public class ContactService {
private ContactMapper contactMapper;

public Page<Contact> getContact(Integer id) {
	// TODO Auto-generated method stub
	Page<Contact> page = new Page<>();
	int pageNo=1;
	page.setPageNo(pageNo);
	int totalElements=(int) contactMapper.getTotalElements();
	page.setTotalElements(totalElements);
	
	
	
	return page;
}

		
}
