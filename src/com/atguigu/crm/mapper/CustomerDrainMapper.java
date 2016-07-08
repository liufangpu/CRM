package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Update;

import com.atguigu.crm.entity.CustomerDrain;


public interface CustomerDrainMapper {

	long getTotalElements();

	List<CustomerDrain> getContent(Map<String, Object> params);
	/*@Update("{call check_drain}")
	void callcheckDrainProcedure();*/
	@Update("{call check_drain}")
	void toDoCustomerDrain();

	void confirm(CustomerDrain customerDrain);

	CustomerDrain getById(Integer id);

	void delay(CustomerDrain drain);

	String getDelay(Integer id);

	


}
