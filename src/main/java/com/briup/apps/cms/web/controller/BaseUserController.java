package com.briup.apps.cms.web.controller;

import java.util.List;

import org.apache.ibatis.executor.BaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.cms.bean.BaseUser;
import com.briup.apps.cms.bean.extend.BaseUserExtend;
import com.briup.apps.cms.service.IBaseUserService;
import com.briup.apps.cms.utils.Message;
import com.briup.apps.cms.utils.MessageUtil;
import com.briup.apps.cms.utils.vm.UserRoleVM;

import io.swagger.annotations.ApiOperation;

@Validated
@RestController
@RequestMapping("/baseUserController")
public class BaseUserController {
	
		@Autowired
		private IBaseUserService baseUserService;
		
		@ApiOperation( value = "这是一个查询所有的接口")
		@GetMapping("findAll")
		public Message findAll() {
		 List<BaseUser> list = baseUserService.findAll();
		 return MessageUtil.success(list);
		}
		
		@ApiOperation(value="级联查询出所有的信息，包括权限")
		@GetMapping("casecadeRoleFindAll")
		 public Message casecadeRoleFindAll() {
			 List<BaseUserExtend> list = baseUserService.cascadeRoleFindAll();
			 return MessageUtil.success(list);
		 }
		
		@ApiOperation(value="通过id删除用户")
		@GetMapping("deleteById")
		public Message deleteById(long id) {
			baseUserService.deleteById(id);
			return MessageUtil.success("删除成功");
		}
		
		 @ApiOperation(value = "保存或更新")
		 @PostMapping(value = "saveOrUpdate")
		public Message saveOrUpdate(BaseUser baseUser) {
			baseUserService.saveOrUpdate(baseUser);
			return MessageUtil.success("更新成功");
		}
		 
		 @ApiOperation(value = "设置权限")
		 @PostMapping(value = "setRoles")
		   public Message setRoles(UserRoleVM userRoleVM){
		        System.out.println(userRoleVM);
		        baseUserService.setRoles(userRoleVM.getId(),userRoleVM.getRoles());
		        return MessageUtil.success("设置成功");
		    }

	
	
}
