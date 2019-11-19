package com.briup.apps.cms.service.Impl;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.apps.cms.bean.BaseUser;
import com.briup.apps.cms.bean.BaseUserExample;
import com.briup.apps.cms.bean.extend.BaseUserExtend;
import com.briup.apps.cms.dao.BaseUserMapper;
import com.briup.apps.cms.dao.extend.BaseUserExtendMapper;
import com.briup.apps.cms.service.IBaseUserService;
import com.briup.apps.cms.utils.CustomerException;
import com.briup.apps.cms.utils.vm.UserVM;

@Service
public class BaseUserServiceImpl  implements  IBaseUserService{

	@Resource
	private BaseUserExtendMapper baseUserExtendMapper;
	
	@Resource
	private BaseUserMapper baseUserMapper;
	
	
	//调用dao的接口去数据库作查询，查看用户是否存在
	@Override
	public BaseUser login(UserVM userVM) throws CustomerException {
		BaseUserExample example = new BaseUserExample();
		//判断查询到的模板的名字是否等于用户的名字
		example.createCriteria().andUsernameEqualTo(userVM.getUsername());
		//查到的是user用户
		List<BaseUser> list=baseUserMapper.selectByExample(example);
		
		if(list.size()<=0) {
			throw new CustomerException("用户不存在");
		}
		BaseUser user = list.get(0);
		//再判断密码.如果用户密码不等于输入的密码 
		if(!user.getPassword().equals(userVM.getPassword())) {
			throw new CustomerException("密码错误");
		}
		//如果用户名密码都对，返回user。在Controller里面调用这个方法获得user
		return user;
	}

	@Override
	public BaseUserExtend findById(long id) {
		
		BaseUserExtend baseUserExtend = baseUserExtendMapper.selectById(id);

		return baseUserExtend;
	}

	@Override
	public List<BaseUser> findAll() {
		BaseUserExample example = new BaseUserExample();
		List<BaseUser> list = baseUserMapper.selectByExample(example);
		return list;
	}

	//级联查出所有。要通过user的id查权限。所以要关联role表。
	@Override
	public List<BaseUserExtend> cascadeRoleFindAll() {
		//这个selectAll是拓展里自己写的sql
		return baseUserExtendMapper.selectAll();
	}

	@Override
	public void saveOrUpdate(BaseUser baseUser) throws CustomerException {
		//如果用户id存在，就进行更新操作
		if(baseUser.getId()!=null) {
			baseUserMapper.updateByPrimaryKey(baseUser);
		}else {
			//进行插入操作。判断用户是否被占用,如果用户名相同则表示该用户被占用
			BaseUserExample  example = new BaseUserExample();
			example.createCriteria().andUsernameEqualTo(baseUser.getUsername());
			List<BaseUser> list = baseUserMapper.selectByExample(example);
			if(list.size()>0) {
				throw new CustomerException("该用户被占用");
			}
			//如果没被占用。设置时间和状态
			  baseUser.setRegisterTime(new Date().getTime());
			  baseUser.setStatus(BaseUserExtend.STATUS_NORMAL);
			  baseUserMapper.insert(baseUser);
		}
		
		
	}

	@Override
	public void changeStatus(long id, String status) throws CustomerException {
		//status设置了两个状态.，调用本类的方法，
		BaseUser user = this.findById(id);
		if(user ==null) {
			throw  new CustomerException("用户不存在");
		}
		//传什么状态就设置成什么状态
		user.setStatus(status);
		baseUserMapper.updateByPrimaryKey(user);
		
	}

	@Override
	public void deleteById(long id) throws CustomerException {
			baseUserMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void setRoles(long id, List<Long> roles) {
	
		 // 根据userid查询自己的角色
		
		 // 用户角色关系,获取所有老的角色
		
		 // [1,2,3] -> [3,4] 添加1,2 => [1,2,3,4]
        // 依次判断新角色是否存在于list中，如果不存在则添加
		
		
		
		// [1,2,3] -> [1,2,3,4]   删除 3,4  => [1,2]
        // 依次判断老的角色是否存在于roles中，如果不存在则删除
		
		
	}


}
