package com.briup.apps.cms.bean.extend;

public class UserExtend {

	
	//拓展一下User的属性，可以看到用户对应的文章
		private ArticleExtend articleExtend;

		public ArticleExtend getArticleExtend() {
			return articleExtend;
		}

		public void setArticleExtend(ArticleExtend articleExtend) {
			this.articleExtend = articleExtend;
		}
		
		
}
