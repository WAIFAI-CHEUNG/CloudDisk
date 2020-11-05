package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pojo.User;

@Mapper
public interface UserMapper {
	public int getUserId(String userName);//获取用户id
	public String checkPassword(String userName);//登录密码校验
	public void userRegister(User user);//用户注册

}
