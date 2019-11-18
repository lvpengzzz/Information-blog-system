package com.briup.apps.cms.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.briup.apps.cms.bean.Category;
import com.briup.apps.cms.bean.CategoryExample;
import com.briup.apps.cms.dao.CategoryMapper;
import com.briup.apps.cms.service.ICategroyService;
import com.briup.apps.cms.utils.CustomerException;

@Service
public class CategoryServiceImpl implements ICategroyService {
	
	
	@Resource
	private CategoryMapper categoryMapper;
	

	@Override
	public List<Category> findAll() {
		return categoryMapper.selectByExample(new CategoryExample());
	}

	@Override
	@Transactional
	public void deleteById(long id) throws CustomerException {
		//先判断一下栏目到底存不存在，去数据库查询看看有没有一样的栏目存在
		Category category = categoryMapper.selectByPrimaryKey(id);
		
		if(category==null) {
			throw new CustomerException("该栏目不存在");
		}
		categoryMapper.deleteByPrimaryKey(id);
	}

	@Override
	@Transactional
	public void batchDelete(long[] ids) throws CustomerException {
		//批量删除。通过id删除，传了个id的数组。通过遍历数组来循环删除
		for(long id:ids) {
			categoryMapper.deleteByPrimaryKey(id);
		}
	}
	@Override
	public void saveOrUpdate(Category category) throws CustomerException {
		if(category.getId()!=null) {
			categoryMapper.updateByPrimaryKey(category);
		}else {
			//判断一下是否重名
			CategoryExample example = new CategoryExample();
			example.createCriteria().andNameEqualTo(category.getName());
			List<Category>  list = categoryMapper.selectByExample(example); 
			//如果重名，则通过example模板能查到。则list有值，则抛出异常
			if(list.size()>0) {
				throw new  CustomerException("该栏目已经存在");
			}
			//如果不重名，把这个栏目插入进去
			categoryMapper.insert(category);
		}
	}
	
}
