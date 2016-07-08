package com.atguigu.crm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.CustomerDrain;
import com.atguigu.crm.mapper.CustomerDrainMapper;
import com.atguigu.crm.orm.Page;
@Service
public class CustomerDrainService {
	@Autowired
	private CustomerDrainMapper customerDrainMapper;
	
	@Transactional
	public void toDoCustomerDrain(){
		customerDrainMapper.toDoCustomerDrain();
		System.out.println("成功");
	}
	
	public Page<CustomerDrain> getPage(int pageNo) {
		Page<CustomerDrain> page = new Page<>();
		page.setPageNo(pageNo);
		int totalElements=(int) customerDrainMapper.getTotalElements();
		page.setTotalElements(totalElements);
		int firstIndex=(page.getPageNo()-1)*page.getPageSize()+1;
		int endIndex=firstIndex+page.getPageSize()-1;
		
		Map<String, Object>params=new HashMap<>();
		params.put("firstIndex", firstIndex);
		params.put("endIndex", endIndex);
		List<CustomerDrain>content=customerDrainMapper.getContent(params);
		page.setContent(content);
		
		return page;
	}

	public void confirm(CustomerDrain customerDrain) {
		customerDrainMapper.confirm(customerDrain);
		
	}

	public CustomerDrain getById(Integer id) {
		return customerDrainMapper.getById(id);
		
	}

	public void delay(CustomerDrain drain,Integer id) {
		
		
		 customerDrainMapper.delay(drain);
		
	}

	
	
	/*@Transactional
	public void callcheckDrainProcedure(){
		customerDrainMapper.callcheckDrainProcedure();
	}*/
}
