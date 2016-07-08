package com.atguigu.crm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.Contact;
import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.Dict;
import com.atguigu.crm.mapper.CustomerMapper;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.utils.MyParseUtils;

@Service
public class CustomerService {
	@Autowired
	private CustomerMapper customerMapper;
	
	@Transactional
	public Page<Customer> getCustomerList(Integer pageNo, Map<String, Object> params) throws Exception {
		Page<Customer> page = new Page<>();
		page.setPageNo(pageNo);
		page.setPageSize(3);
		Map<String, Object> map = MyParseUtils.parseRequestParams2MyBatisParams(params);
		int elements =  (int) customerMapper.getTotalElements(map);
		page.setTotalElements(elements);
		
		Integer startIndex = (pageNo-1)*3 +1;
		Integer endIndex = startIndex+3;
		
		map.put("startIndex", startIndex);
		map.put("endIndex", endIndex);
		System.out.println(map);
		List<Customer> content =  customerMapper.getCustomerList(map);
		//getCustomerList
		page.setContent(content);
		
		return page;
	}
	@Transactional
	public List<Dict> getRegionList(String str) {
		
		return customerMapper.getRegionList(str);
	}
	@Transactional
	public Customer getById(Integer customerId) {
		return customerMapper.getById(customerId);
	}
	@Transactional
	public void deleteCustomer(Integer id) {
		
		customerMapper.selectCustomer(id,"删除");
		
		
	}
	
	
	
	@Transactional
	public Page<Customer> getPage(int pageNo) {
		Page<Customer> page = new Page<Customer>();
		page.setPageNo(pageNo);
		int totalElements=(int) customerMapper.getTotalElements(null);
		System.out.println(totalElements);
		page.setTotalElements(totalElements);
		
		int firstIndex=(pageNo-1)*page.getPageSize()+1;
		
		int endIndex=firstIndex+page.getPageSize()-1;
		
		Map<String, Object>params=new HashMap<>();
		params.put("firstIndex", firstIndex);
		params.put("endIndex", endIndex);
		List<Customer>content=customerMapper.getContent(params);
		
		page.setContent(content);
		
		
		
		
		
		
		return page;
	}
	public void update(Customer customer) {
		customerMapper.update(customer);
		
	}
	
	
	
	
	
	public List<String> getModelList(String string) {
		
		return customerMapper.getModelList(string);
	}
	public List<Contact> getManagerList(Integer id) {
		
		return customerMapper.getManagerList(id);
	}
	
}
