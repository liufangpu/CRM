package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import com.atguigu.crm.entity.SalesChance;


public interface SalesChanceMapper {
	void save(SalesChance chance);
	long getTotalElements();
	
	List<SalesChance>getContent(Map<String, Object>params);
	void delete(int id);
	SalesChance getById(Integer id);
	void update(SalesChance chance);
	long getTotalElements2(Map<String, Object> params);
	List<SalesChance> getContent2(Map<String, Object> map);
	
	
	
}
