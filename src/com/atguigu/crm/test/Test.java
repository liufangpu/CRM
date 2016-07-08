package com.atguigu.crm.test;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class Test {
	
	public static void main(String[] args) throws SQLException {
		ApplicationContext ioc=new ClassPathXmlApplicationContext("applicationContext.xml");
		DataSource bean = ioc.getBean(DataSource.class);
		Connection connection = bean.getConnection();
		System.out.println(connection);
	}

	
}
