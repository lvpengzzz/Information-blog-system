package com.briup.apps.cms.service.Impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.apps.cms.bean.User;
import com.briup.apps.cms.bean.extend.UserExtend;
import com.briup.apps.cms.dao.UserMapper;
import com.briup.apps.cms.service.IBaseUserService;

@Service
public class BaseUserServiceImpl  implements  IBaseUserService{

	@Resource
	private UserMapper userMapper;
	
	@Override
	public User findAllUserById(long id) {
		return  userMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<UserExtend> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
		
}
