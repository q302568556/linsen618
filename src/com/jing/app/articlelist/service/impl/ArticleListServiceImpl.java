package com.jing.app.articlelist.service.impl;

import com.jing.app.articlelist.dao.ArticleListDao;
import com.jing.app.articlelist.service.ArticleListService;
import com.jing.app.common.entity.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleListServiceImpl implements ArticleListService {

	private ArticleListDao articleListDao;
	//依赖注入DAO组件所需的setter方法
	public void setArticleListDao(ArticleListDao articleListDao) {
		this.articleListDao = articleListDao;
	}

	//获得文章列表
	public List<Article> queryArticleList(String selectedDate) {
		//调用Dao组件的方法来实现业务逻辑
		List<Article> articleList = new ArrayList<Article>();
		articleList = articleListDao.queryArticleList(selectedDate);
		return articleList;
	}
}
