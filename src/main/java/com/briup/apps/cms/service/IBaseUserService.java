package com.briup.apps.cms.service;


import java.util.List;

import com.briup.apps.cms.bean.BaseUser;
import com.briup.apps.cms.bean.extend.BaseUserExtend;
import com.briup.apps.cms.utils.CustomerException;
import com.briup.apps.cms.utils.vm.UserVM;

public interface IBaseUserService {

	
	BaseUser login(UserVM userVM) throws CustomerException;
	
    BaseUserExtend findById(long id);

    List<BaseUser> findAll();
    //查询所有包括权限
    List<BaseUserExtend> cascadeRoleFindAll();

    void saveOrUpdate(BaseUser baseUser) throws CustomerException;

    void changeStatus(long id,String status) throws CustomerException;
    
    void deleteById(long id) throws CustomerException;
    //设置权限
    void setRoles(long id, List<Long> roles);
}

