package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.Contact;
import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.Dict;

public interface CustomerMapper {

	long getTotalElements(Map<String, Object> map);
	Customer getById(@Param("id")Integer customerId);
	List<Customer> getCustomerList(Map<String, Object> map);

	List<Dict> getRegionList(@Param("type") String type);
	void selectCustomer(@Param("id")Integer id,@Param("state")String state);
	
	
//	long getTotalElements();
	
	
	List<Customer> getContent(Map<String, Object> params);
	void update(Customer customer);
	
	
	List<String> getModelList(@Param("type")String string);
	List<Contact> getManagerList(Integer id);
	
}
