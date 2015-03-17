package com.jing.app.article.service.impl;

import com.jing.app.article.dao.ArticleDao;
import com.jing.app.article.service.ArticleService;
import com.jing.app.common.entity.Article;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
  public void addArticle(Article article, String filelink, String articleimgPath) {
    article.setArticledate(new Date());
    /* --------为了防止保存前上传又删除的图片仍然保留在文件夹中所做的处理：
               先保存到临时文件夹再遍历html代码将需要保存的图片转移到正式文件夹 begin------- */
    //将文章内容中图片的临时保存路径替换为正式保存路径
    String articlecontent = article.getArticlecontent();
    article.setArticlecontent(articlecontent.replace(filelink, articleimgPath));
    //获取文章中的图片名，将文章图片从临时文件夹转移到正式文件夹
    List<String> articleimgNameList = createArticleimgNameList(articlecontent);
    transferArticleimg(filelink, articleimgPath, articleimgNameList);
    /* --------为了防止保存前已删除的图片仍然保留在文件夹中所做的处理 end---------------- */
    //保存文章到数据库
    articleDao.addArticle(article);
  }

  //修改文章
  public void updateArticle(Article article, String filelink, String articleimgPath) {
    article.setArticledate(new Date());
    /* --------为了防止保存前上传又删除的图片和修改时删除的图片仍然保留在文件夹中所做的处理：
               新增的图片先保存到临时文件夹再遍历html代码将需要保存的图片转移到正式文件夹，
               删除的图片从正式文件夹中删除 begin------- */
    //将文章内容中图片的临时保存路径替换为正式保存路径
    String articlecontent = article.getArticlecontent();
    article.setArticlecontent(articlecontent.replace(filelink, articleimgPath));
    //获取新旧文章中的图片名，比对新旧文章的图片名，
    //将修改后新增的文章图片从临时文件夹转移到正式文件夹，将修改后删除的文章图片从正式文件夹中删除
    List<String> articleimgNameList_new = createArticleimgNameList(articlecontent); //新文章图片列表
    String articlecontent_old =
        articleDao.queryArticle(article.getArticleid()).getArticlecontent(); //旧文章内容
    List<String> articleimgNameList_old =
        createArticleimgNameList(articlecontent_old);//旧文章图片列表
    //比对新旧文章图片列表，获取新增图片列表和删除图片列表
    List<String> articleimgNameList_add = new ArrayList<String>(); //新增图片列表
    List<String> articleimgNameList_del = new ArrayList<String>(); //删除图片列表
    for(String articleimgName : articleimgNameList_new) { //获得新增图片列表
      if(!articleimgNameList_old.contains(articleimgName)) {
        articleimgNameList_add.add(articleimgName);
      }
    }
    for(String articleimgName : articleimgNameList_old) { //获得删除图片列表
      if(!articleimgNameList_new.contains(articleimgName)) {
        articleimgNameList_del.add(articleimgName);
      }
    }
    //将新增图片从临时文件夹转移到正式文件夹，将删除图片从正式文件夹中删除
    transferArticleimg(filelink, articleimgPath, articleimgNameList_add);
    deleteArticleimg(articleimgPath, articleimgNameList_del);
    /* ----为了防止保存前上传又删除的图片和修改时删除的图片仍然保留在文件夹中所做的处理 end---- */
    //修改数据库中的文章
    articleDao.updateArticle(article);
  }

  //删除文章
  public void deleteArticle(String articleid, String articleimgPath) {
    /* --------从正式文件夹中删除文章中的图片 begin------- */
    String articlecontent = articleDao.queryArticle(articleid).getArticlecontent();
    //获取文章中的图片名，将文章图片从正式文件夹中删除
    List<String> articleimgNameList = createArticleimgNameList(articlecontent);
    deleteArticleimg(articleimgPath, articleimgNameList);
    /* --------从正式文件夹中删除文章中的图片 end---------------- */
    //删除数据库中的文章
    articleDao.deleteArticle(articleid);
  }

  //从文章内容中读取所有的图片名称（利用正则表达式）
  public List<String> createArticleimgNameList(String articleContent) {
    List<String> articleimgNameList = new ArrayList<String>();
    //匹配img标签
    String imgRegex = "(<img.*src\\s*=\\s*(.*?)[^>]*?>)";
    Pattern imgPattern = Pattern.compile(imgRegex, Pattern.CASE_INSENSITIVE);
    Matcher imgMatcher = imgPattern.matcher(articleContent);
    while(imgMatcher.find()) {
      String imgGroup = imgMatcher.group();
      //匹配img标签的src属性
      String imgSrcRegex = "src\\s*=\\s*\"?(.*?)(\"|>|\\s+)";
      Matcher imgSrcMatcher = Pattern.compile(imgSrcRegex).matcher(imgGroup);
      while(imgSrcMatcher.find()) {
        String imgSrc = imgSrcMatcher.group(1);
        String articleimgName = imgSrc.substring(imgSrc.lastIndexOf("/") + 1); //获取图片名称
        articleimgNameList.add(articleimgName);
      }
    }
    return articleimgNameList;
  }

  //批量将文章图片从临时文件夹转移到正式文件夹
  public void transferArticleimg(String filelink, String articleimgPath,
                                 List<String> articleimgNameList) {
    //将相对路径改为绝对路径
    filelink = ServletActionContext.getServletContext().getRealPath(filelink);
    articleimgPath = ServletActionContext.getServletContext().getRealPath(articleimgPath);
    for(String articleimgName : articleimgNameList) {
      File articleimg_temp = new File(filelink + "/" + articleimgName);
      File articleimg = new File(articleimgPath + "/" + articleimgName);
      articleimg_temp.renameTo(articleimg); //文章图片转移（剪切）到正式文件夹
    }
  }

  //批量将文章图片从正式文件夹中删除
  public void deleteArticleimg(String articleimgPath, List<String> articleimgNameList) {
    //将相对路径改为绝对路径
    articleimgPath = ServletActionContext.getServletContext().getRealPath(articleimgPath);
    for(String articleimgName : articleimgNameList) {
      File articleimg = new File(articleimgPath + "/" + articleimgName);
      if(articleimg.exists() && articleimg.isFile()) {
        articleimg.delete();
      }
    }
  }

}
