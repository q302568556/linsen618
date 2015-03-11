package com.jing.app.article.service.impl;

import com.jing.app.article.dao.ArticleDao;
import com.jing.app.article.service.ArticleService;
import com.jing.app.common.entity.Article;

import java.util.Date;

/**
 * Created by Jing on 2014/12/5.
 */
public class ArticleServiceImpl implements ArticleService{
  //DAO组件
  private ArticleDao articleDao;
  //依赖注入DAO组件所需的setter方法
  public void setArticleDao(ArticleDao articleDao) {
    this.articleDao = articleDao;
  }

  //查询文章
  public Article queryArticle(String articleid) {
    //调用Dao组件的方法来实现业务逻辑
    Article article = articleDao.queryArticle(articleid);
    return article;
  }

  //新增文章
  public void addArticle(Article article) {
    article.setArticledate(new Date());
    //调用Dao组件的方法来实现业务逻辑
    articleDao.addArticle(article);
  }

  //修改文章
  public void updateArticle(Article article) {
    article.setArticledate(new Date());
    //调用Dao组件的方法来实现业务逻辑
    articleDao.updateArticle(article);
  }

  //删除文章
  public void deleteArticle(String articleid) {
    //调用Dao组件的方法来实现业务逻辑
    articleDao.deleteArticle(articleid);
  }
}
