package com.jing.app.articlelist.dao;

import com.jing.app.common.entity.Article;

import java.util.List;

public interface ArticleListDao {
    //获得文章列表
	public List<Article> queryArticleList(String selectedDate);
}
