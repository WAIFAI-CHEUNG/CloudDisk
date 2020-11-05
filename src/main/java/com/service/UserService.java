package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserMapper;
import com.pojo.CdFile;
import com.pojo.User;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;
	
	/* 登录密码校验 */
	public int loginCheck(String userName,String password) {
		/* 判断用户登录密码，正确为1，错误为0 */
		String checkPassword = userMapper.checkPassword(userName);
		if(checkPassword==null) return 0; 
		if(checkPassword.equals(password)) {
			return 1;
		}
		return 0;
	}
	
	/* 获取登录用户id */
	public int getUserId(String userName) {
		return userMapper.getUserId(userName);
	}
	
	/* 注册账号 */
	public void UserRegister(User user) {
		userMapper.userRegister(user);
	}
	

}
