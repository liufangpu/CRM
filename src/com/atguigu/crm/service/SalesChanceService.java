package com.atguigu.crm.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.mapper.SalesChanceMapper;
import com.atguigu.crm.orm.Page;
@Service
public class SalesChanceService {
	@Autowired
	private SalesChanceMapper salesChanceMapper;
	@Transactional(readOnly=true)
	public Page<SalesChance> getPage(int pageNo) {
//		创建 Page 对象
		Page<SalesChance>page=new Page<>();
//		设置 pageNo 属性. 同时校验 pageNo 的合法性: 校验其是否大于 0
		page.setPageNo(pageNo);
		
//		获取总的记录数. 校验 pageNo 的合法性: 此时已经可以由 总的记录数和 pageSize 计算出总页数, 
		//进而校验 pageNo 是否在合法的区间
		int totalElements=(int) salesChanceMapper.getTotalElements();
		//System.out.println(totalElements);
		page.setTotalElements(totalElements);
		
		
//		查询当前页面的 content
		int firstIndex=(pageNo-1)*page.getPageSize()+1;
		int endIndex=firstIndex+page.getPageSize();
		Map<String, Object>params=new HashMap<String,Object>();
		
		params.put("firstIndex", firstIndex);
		params.put("endIndex", endIndex);
		List<SalesChance>content=salesChanceMapper.getContent(params);
//		为 Page 对象的 content 赋值
		page.setContent(content);
		System.out.println(page.getContent());
//		返回 Page 对象
		return page;
	}
	public void save(SalesChance chance) {
		salesChanceMapper.save(chance);
	}
	public void delete(int id) {
		salesChanceMapper.delete(id);
		
	}
	public SalesChance getById(Integer id) {
		
		return salesChanceMapper.getById(id);
	}
	public void update(SalesChance chance) {
		
		salesChanceMapper.update(chance);
	}
	@Transactional(readOnly=true)
	public Page<SalesChance> getPage2(int pageNo,Map<String, Object>requestParams) throws ParseException {
//		创建 Page 对象
		Page<SalesChance>page=new Page<>();
//		设置 pageNo 属性. 同时校验 pageNo 的合法性: 校验其是否大于 0
		page.setPageNo(pageNo);
		
//		获取总的记录数. 校验 pageNo 的合法性: 此时已经可以由 总的记录数和 pageSize 计算出总页数, 
		//进而校验 pageNo 是否在合法的区间
		Map<String, Object> params = parseRequestParams2MyBatisParams(requestParams);
		int totalElements=(int) salesChanceMapper.getTotalElements2(params);
		//System.out.println(totalElements);
		page.setTotalElements(totalElements);
		//查询当前页面的内容
		int firstIndex=(pageNo-1)*page.getPageSize()+1;
		int endIndex=firstIndex+page.getPageSize()-1;
		
		Map<String, Object>map=new HashMap<>();
		map.put("firstIndex", firstIndex);
		map.put("endIndex", endIndex);
		
		List<SalesChance>content=salesChanceMapper.getContent2(map);
		
		page.setContent(content);

		return page;
		
	}
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private Map<String, Object> parseRequestParams2MyBatisParams(Map<String, Object> requestParams) throws ParseException {
	Map<String, Object> result = new HashMap<>();
		
		for(Map.Entry<String, Object> entry: requestParams.entrySet()){
			String key = entry.getKey();
			Object val = entry.getValue();
			
			if(key.startsWith("D_")){
				key = key.substring(2);
				val = dateFormat.parse((String)val);
			}
			if(key.startsWith("LIKE_")){
				key = key.substring(5);
				val = "%" + val + "%";
			}
			
			result.put(key, val);
		}
		
		return result;
	}
	

}
