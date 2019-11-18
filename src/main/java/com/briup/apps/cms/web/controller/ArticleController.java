package com.briup.apps.cms.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.cms.bean.Article;
import com.briup.apps.cms.bean.extend.ArticleExtend;
import com.briup.apps.cms.service.IArticleService;
import com.briup.apps.cms.utils.Message;
import com.briup.apps.cms.utils.MessageUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Validated
@RestController
@RequestMapping("/article")
public class ArticleController {

	
	//自动注入IAticleService 的实现类。
	@Autowired
	private IArticleService articleService;
	
	
	@ApiOperation(value = "查询所有")//在swagger上写提示
	@GetMapping("findAll")
	public Message findAll() {
		List <Message> list= new ArrayList<>();
		return MessageUtil.success(list);
	}
	
	//利用message統一結果返回，方便前端處理
	
	  @ApiOperation(value="级联查询栏目")
	  @GetMapping("findAllWithCategory")
	    public Message findAllWithCategory(){
	        List<ArticleExtend> list = articleService.findAllWithCategory();
	        return MessageUtil.success(list);
	    }
	  
	  
	  @ApiOperation(value="通过参数查询")
	  //对参数的操作
	  @ApiImplicitParams(
			  @ApiImplicitParam(name="id",value="这是一个组件",paramType = "query")
			  ) 
	  @GetMapping("findById")
	    public Message findById(long id){
	        ArticleExtend articleExtend = articleService.findById(id);
	        return MessageUtil.success(articleExtend);
	  }
	  
	  //在Swagger上进行提示
	  @ApiOperation(value="保存或更新")
	  @ApiImplicitParams({
		  @ApiImplicitParam(name="id",value="编号",paramType = "form"),
		  @ApiImplicitParam(name="title",value="标题",paramType = "form"),
		  @ApiImplicitParam(name="content",value="内容",paramType = "form"),
		  @ApiImplicitParam(name="source",value="源内容",paramType = "form"),
		  @ApiImplicitParam(name="categoryId",value="栏目id",paramType = "form"),
		  @ApiImplicitParam(name="authorId",value="作者id",paramType = "form"),
	  }) 
	  @PostMapping("savaOrUpdate")
	  public Message savaOrUpdate(Long id,
			  @NotNull String title,@NotNull String content,String source,Long categoryId,Long authorId) {
		
		  //通过传参什么参数就更新什么Article中的什么内容的操作
		  Article article = new Article();
		article.setId(id);
		article.setTitle(title);
		article.setContent(content);
		article.setSource(source);
		article.setCategoryId(categoryId);
		article.setAuthorId(authorId);
		articleService.saveOrUpdate(article);
		return MessageUtil.success("更新成功");
	  }
	  
	  
	  
	  
	
}
