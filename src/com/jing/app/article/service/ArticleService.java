package com.jing.app.article.service;

import com.jing.app.common.entity.Article;

import java.io.File;
import java.io.IOException;

/**
 * Created by Jing on 2014/12/5.
 */
public interface ArticleService {
  //查询文章
  public Article queryArticle(String articleid);

  //新增文章
  public void addArticle(Article article, String filelink, String articleImgPath);

  //修改文章
  public void updateArticle(Article article, String filelink, String articleImgPath);

  //删除文章
  public void deleteArticle(String articleid, String articleImgPath);

  //保存文章图片
  public  void saveArticleImg(String filelink, File file) throws IOException;
}
