package com.jing.app.article.dao.impl;

import com.jing.app.article.dao.ArticleDao;
import com.jing.app.common.entity.Article;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Created by Jing on 2014/12/5.
 */
public class ArticleDaoImpl extends HibernateDaoSupport implements ArticleDao {

  //查询文章
  public Article queryArticle(String articleid) {
    Article article = new Article();
    article = (Article)getHibernateTemplate().get(Article.class, articleid);
    return  article;
  }

  //新增文章
  public void addArticle(Article article) {
    getHibernateTemplate().save(article);
  }

  //修改文章
  public void updateArticle(Article article) {
    //为了防止hibernate缓存中已有相同id的对象，此时如果直接update传入的对象会报错：
    //a different object with the same identifier value was already associated with the session
    Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
    Article article_old = queryArticle(article.getArticleid());
    session.evict(article_old); //清除缓存中的相同id的对象，另一种方法是对article_old设值并对article_old进行update
    getHibernateTemplate().update(article);
  }

  //删除文章
  public void deleteArticle(String articleid) {
    //为了防止hibernate缓存中已有相同id的对象，此时如果new一个对象再删除会报错：
    //a different object with the same identifier value was already associated with the session
    Article article = queryArticle(articleid);
    getHibernateTemplate().delete(article);
  }
}
