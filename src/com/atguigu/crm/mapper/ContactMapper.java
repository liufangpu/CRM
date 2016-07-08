package com.atguigu.crm.mapper;

import java.util.List;

import com.atguigu.crm.entity.Contact;

public interface ContactMapper {

	List<Contact> getContent(Integer id);

	long getTotalElements();

}
