package com.briup.apps.cms.service;


import java.util.List;

import com.briup.apps.cms.bean.User;
import com.briup.apps.cms.bean.extend.UserExtend;

public interface IBaseUserService {

	//通过user的id查询到所有的文章。以及文章的评论和栏目
   User findAllUserById(long id);
   
   //查询所有的用户
   List<UserExtend> findAll();
		   
   
   
   
	
}
