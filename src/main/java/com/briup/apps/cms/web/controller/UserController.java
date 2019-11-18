package com.briup.apps.cms.web.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.cms.bean.User;
import com.briup.apps.cms.service.IBaseUserService;
import com.briup.apps.cms.utils.Message;
import com.briup.apps.cms.utils.MessageUtil;
import com.briup.apps.cms.utils.VM.UserVM;

import io.swagger.annotations.ApiOperation;

@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
	private IBaseUserService  userServiceImpl;
	
	@GetMapping("findAllById")
	public Message findAllById(long id) {
		User user = userServiceImpl.findAllUserById(id);
		return MessageUtil.success(user);
	}
	
	
	@ApiOperation(value="这是一个登入接口")
	//写了个登入接口，传username和password参数给前台。使用UserVM封装了username
	@PostMapping("login")
	public Message login(@RequestBody UserVM userVM) {
		
		Map<String,String> map = new HashMap<>();
		map.put("token","admin-token");
		
				return MessageUtil.success(map);
	}
	
}
