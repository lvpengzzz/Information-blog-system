package com.briup.apps.cms.service;

import java.util.List;

import com.briup.apps.cms.bean.Category;
import com.briup.apps.cms.utils.CustomerException;

public interface ICategroyService {

	List<Category> findAll();
	
	void saveOrUpdate(Category category) throws CustomerException;

	void deleteById(long id) throws CustomerException;

	void batchDelete(long ids[]) throws CustomerException;
	
}
