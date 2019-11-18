package com.briup.apps.cms.service.Impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.apps.cms.bean.Article;
import com.briup.apps.cms.bean.ArticleExample;
import com.briup.apps.cms.bean.extend.ArticleExtend;
import com.briup.apps.cms.dao.ArticleMapper;
import com.briup.apps.cms.dao.extend.ArticleExtendMapper;
import com.briup.apps.cms.service.IArticleService;
import com.briup.apps.cms.utils.CustomerException;

@Service
public class ArticleServiceImpl implements IArticleService{

		
	@Resource
	private ArticleMapper articleMapper;
	
	@Resource
	private ArticleExtendMapper articleExtendMapper;
	
	@Override
	public List<Article> findAll() {
		return articleMapper.selectByExample(new ArticleExample());
	}

	@Override
	public List<ArticleExtend> findAllWithCategory() {
		return articleExtendMapper.selectAll();
	}

	@Override
	public ArticleExtend findById(long id) {
		return articleExtendMapper.selectById(id);
	}

	@Override
	public void saveOrUpdate(Article article) throws CustomerException {
		if(article.getId()!=null) {
			articleMapper.updateByPrimaryKey(article);
		}else {
			//自定义异常。
			ArticleExample example = new ArticleExample();
			
			example.createCriteria().andTitleEqualTo(article.getTitle());
			
			List<Article> articles=articleMapper.selectByExample(example);
			
			if(articles.size()>0) {
				throw new CustomerException("标题不能重复");
			}
			article.setPublishTime(new Date().getTime());
			article.setStatus(ArticleExtend.STATUS_CHECK_PASS);
			article.setThumbDown(0l);
			article.setThumbUp(0l);
			articleMapper.insert(article);
		}
	}
	
	

	
}
