package com.briup.apps.cms.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.apps.cms.bean.Test;
import com.briup.apps.cms.bean.TestExample;
import com.briup.apps.cms.dao.TestMapper;
import com.briup.apps.cms.service.ITestService;

@Service
public class TestServiceImlp  implements ITestService{

	//Spring自动生成的代理类，相当于把之前mybatis生成mapper交给Spring代理了，这里是Spring代理出来的。用@Resource自动注入。
	 @Resource
	    private TestMapper testMapper;

	    @Override
	    public void saveOrUpdate(Test test) {
	        if(test.getId()!=null){
	            testMapper.updateByPrimaryKey(test);
	        } else {
	            testMapper.insert(test);
	        }
	    }

	    @Override
	    public List<Test> findAll() {
	        TestExample example = new TestExample();
	        return testMapper.selectByExample(example);
	    }

}
