package com.briup.apps.cms.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.cms.bean.Category;
import com.briup.apps.cms.service.ICategroyService;
import com.briup.apps.cms.utils.Message;
import com.briup.apps.cms.utils.MessageUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Validated
@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private  ICategroyService categoryServeice;
	
	@ApiOperation(value="查找所有文章")
	@GetMapping("findAll")
	public Message findAll() {
		//一个栏目查询到的文章肯定有很多，所以用List集合装。
		List<Category>  list = categoryServeice.findAll();
		//查询成功返回消息。把list显示出来
		return MessageUtil.success(list);
	}
	
	@ApiOperation(value="通过id删除")
	@ApiImplicitParams(
			@ApiImplicitParam(name="id",value="这个是栏目的id",paramType = "query")
			)
	@GetMapping("deleteById")
	public Message deleteById(long id) {
		//通过CategoryService调用接口中的deleteById方法。再把id传进去。前端调用这个接口的时候就会执行这个方法
			categoryServeice.deleteById(id);
			//这个封装的信息是为了方便前台页面显示
		return MessageUtil.success("删除成功");
	}
	
	@ApiOperation(value="批量删除")
	@PostMapping("batchDelete")
	public Message batchDelete(long ids[]) {
		categoryServeice.batchDelete(ids);
		return MessageUtil.success("删除成功");
	}
	
	
	@ApiOperation(value="插入或更新")
	@ApiImplicitParams({
			@ApiImplicitParam(name="id",value="这个是栏目的id",paramType = "form"),
			@ApiImplicitParam(name="name",value="这是栏目的名字",paramType = "form"),
			@ApiImplicitParam(name="description",value="这是栏目的描述",paramType = "form"),
			@ApiImplicitParam(name="no",value="栏目的编号",paramType = "form"),
			@ApiImplicitParam(name="parentId",value="父栏目",paramType = "form"),}
			)
	@PostMapping("saveOrUpdate")
	public Message saveOrUpdate(Long id ,String name,String description,Long no,Long parentId) {
	
		Category  category = new Category();
		category.setId(id);
		category.setName(name);
		category.setDescription(description);
		category.setNo(no);
		category.setParentId(parentId);
		categoryServeice.saveOrUpdate(category);
		return MessageUtil.success("更新成功");
	}
}