package com.atguigu.crm.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.atguigu.crm.entity.User;

public interface UserMapper {
	
	@Select("SELECT u.id, u.name, password, u.enabled, salt,r.name as\"role.name\""
		  + "FROM users u "
		  + "left outer join roles r "
		  + "on u.role_id=r.id "
		  + "WHERE u.name = #{name}"
		 )
	User getByName(@Param("name") String name);
	
}
