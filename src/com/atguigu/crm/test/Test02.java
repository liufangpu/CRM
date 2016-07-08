package com.atguigu.crm.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.SalesChanceService;

public class Test02 {

	private ApplicationContext ctx = null;
	private SalesChanceService salesChanceService = null;
	
	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		salesChanceService = ctx.getBean(SalesChanceService.class);
	}
	
	@Test
	public void testPage() {
		Page<SalesChance> page = salesChanceService.getPage(2);
		
		System.out.println(page.getTotalElements());
		System.out.println(page.getTotalPage());
	}

}
