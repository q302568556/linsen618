package com.jing.app.articlelist.service;

import com.jing.app.common.entity.Article;

import java.util.List;

public interface ArticleListService {
	//获取文章列表
	public List<Article> queryArticleList(String selectedDate);
}
