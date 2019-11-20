package com.briup.apps.cms.web.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.cms.bean.BaseUser;
import com.briup.apps.cms.bean.extend.BaseUserExtend;
import com.briup.apps.cms.service.IBaseUserService;
import com.briup.apps.cms.utils.JwtTokenUtil;
import com.briup.apps.cms.utils.Message;
import com.briup.apps.cms.utils.MessageUtil;
import com.briup.apps.cms.utils.vm.UserVM;

import io.swagger.annotations.ApiOperation;

@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
	private IBaseUserService  baseUserServiceImpl;
	
	
	
	@ApiOperation(value="这是一个登入接口")
	@PostMapping("login")
	public Message login(@RequestBody UserVM userVM) {
		//调用service方法认真用户名密码
		BaseUser user =baseUserServiceImpl.login(userVM);
		//认真成功产生一个token
		String token= JwtTokenUtil.createJWT(user.getId(), user.getUsername());
		
		Map<String,String> map = new HashMap<>();
		map.put("token",token);
		return MessageUtil.success(map);
	}
	
	@ApiOperation(value="这是一个用户信息接口，如果想要获得用户的详细信息，调用此接口")
	@GetMapping("info")
	public Message info(String token,HttpSession session) {
		//通过token获取用户的基本信息
		long id = Long.parseLong(JwtTokenUtil.getUserId(token,JwtTokenUtil.base64Secret));
		 BaseUserExtend baseUserExtend = baseUserServiceImpl.findById(id);
		return MessageUtil.success(baseUserExtend);
	}
	
	 @PostMapping("logout")
	    public Message logout(){
	        return MessageUtil.success("退出成功");
	    }
}
