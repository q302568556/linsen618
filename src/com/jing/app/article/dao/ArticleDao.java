package com.jing.app.article.dao;

import com.jing.app.common.entity.Article;

/**
 * Created by Jing on 2014/12/5.
 */
public interface ArticleDao {
  //查询文章
  public Article queryArticle(String articleid);

  //新增文章
  public void addArticle(Article article);

  //修改文章
  public void updateArticle(Article article);

  //删除文章
  public void deleteArticle(String articleid);
}
