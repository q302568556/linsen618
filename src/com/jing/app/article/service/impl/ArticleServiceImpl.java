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
  public void addArticle(Article article, String filelink, String articleImgPath) {
    article.setArticledate(new Date());
    /* --------为了防止保存前上传又删除的图片仍然保留在文件夹中所做的处理：
               先保存到临时文件夹再遍历html代码将需要保存的图片转移到正式文件夹 begin------- */
    //将文章内容中图片的临时保存路径替换为正式保存路径
    String articlecontent = article.getArticlecontent();
    article.setArticlecontent(articlecontent.replace(filelink, articleImgPath));
    //获取文章中的图片名，将文章图片从临时文件夹转移到正式文件夹
    List<String> articleImgNameList = createArticleImgNameList(articlecontent);
    transferArticleImg(filelink, articleImgPath, articleImgNameList);
    /* --------为了防止保存前已删除的图片仍然保留在文件夹中所做的处理 end---------------- */
    //保存文章到数据库
    articleDao.addArticle(article);
  }

  //修改文章
  public void updateArticle(Article article, String filelink, String articleImgPath) {
    article.setArticledate(new Date());
    /* --------为了防止保存前上传又删除的图片和修改时删除的图片仍然保留在文件夹中所做的处理：
               新增的图片先保存到临时文件夹再遍历html代码将需要保存的图片转移到正式文件夹，
               删除的图片从正式文件夹中删除 begin------- */
    //将文章内容中图片的临时保存路径替换为正式保存路径
    String articlecontent = article.getArticlecontent();
    article.setArticlecontent(articlecontent.replace(filelink, articleImgPath));
    //获取新旧文章中的图片名，比对新旧文章的图片名，
    //将修改后新增的文章图片从临时文件夹转移到正式文件夹，将修改后删除的文章图片从正式文件夹中删除
    List<String> articleImgNameList_new = createArticleImgNameList(articlecontent); //新文章图片列表
    String articlecontent_old =
        articleDao.queryArticle(article.getArticleid()).getArticlecontent(); //旧文章内容
    List<String> articleImgNameList_old =
        createArticleImgNameList(articlecontent_old);//旧文章图片列表
    //比对新旧文章图片列表，获取新增图片列表和删除图片列表
    List<String> articleImgNameList_add = new ArrayList<String>(); //新增图片列表
    List<String> articleImgNameList_del = new ArrayList<String>(); //删除图片列表
    for(String articleImgName : articleImgNameList_new) { //获得新增图片列表
      if(!articleImgNameList_old.contains(articleImgName)) {
        articleImgNameList_add.add(articleImgName);
      }
    }
    for(String articleImgName : articleImgNameList_old) { //获得删除图片列表
      if(!articleImgNameList_new.contains(articleImgName)) {
        articleImgNameList_del.add(articleImgName);
      }
    }
    //将新增图片从临时文件夹转移到正式文件夹，将删除图片从正式文件夹中删除
    transferArticleImg(filelink, articleImgPath, articleImgNameList_add);
    deleteArticleImg(articleImgPath, articleImgNameList_del);
    /* ----为了防止保存前上传又删除的图片和修改时删除的图片仍然保留在文件夹中所做的处理 end---- */
    //修改数据库中的文章
    articleDao.updateArticle(article);
  }

  //删除文章
  public void deleteArticle(String articleid, String articleImgPath) {
    /* --------从正式文件夹中删除文章中的图片 begin------- */
    String articlecontent = articleDao.queryArticle(articleid).getArticlecontent();
    //获取文章中的图片名，将文章图片从正式文件夹中删除
    List<String> articleImgNameList = createArticleImgNameList(articlecontent);
    deleteArticleImg(articleImgPath, articleImgNameList);
    /* --------从正式文件夹中删除文章中的图片 end---------------- */
    //删除数据库中的文章
    articleDao.deleteArticle(articleid);
  }

  //从文章内容中读取所有的图片名称（利用正则表达式）
  public List<String> createArticleImgNameList(String articleContent) {
    List<String> articleImgNameList = new ArrayList<String>();
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
        String articleImgName = imgSrc.substring(imgSrc.lastIndexOf("/") + 1); //获取图片名称
        articleImgNameList.add(articleImgName);
      }
    }
    return articleImgNameList;
  }

  //批量将文章图片从临时文件夹转移到正式文件夹
  public void transferArticleImg(String filelink, String articleImgPath, List<String> articleImgNameList) {
    //将相对路径改为绝对路径
    filelink = ServletActionContext.getServletContext().getRealPath(filelink);
    articleImgPath = ServletActionContext.getServletContext().getRealPath(articleImgPath);
    for(String articleImgName : articleImgNameList) {
      File articleImg_temp = new File(filelink + "/" + articleImgName);
      File articleImg = new File(articleImgPath + "/" + articleImgName);
      articleImg_temp.renameTo(articleImg); //文章图片转移（剪切）到正式文件夹
    }
  }

  //批量将文章图片从正式文件夹中删除
  public void deleteArticleImg(String articleImgPath, List<String> articleImgNameList) {
    //将相对路径改为绝对路径
    articleImgPath = ServletActionContext.getServletContext().getRealPath(articleImgPath);
    for(String articleImgName : articleImgNameList) {
      File articleImg = new File(articleImgPath + "/" + articleImgName);
      if(articleImg.exists() && articleImg.isFile()) {
        articleImg.delete();
      }
    }
  }

}
