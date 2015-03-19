package com.jing.app.article.action;

import com.jing.app.article.service.ArticleService;
import com.jing.app.common.entity.Article;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.UUID;

/**
 *
 */
public class ArticleAction extends ActionSupport {
  //系统的业务逻辑组件
  private ArticleService articleService;
  //设置注入业务逻辑组件的setter方法
  public void setArticleService(ArticleService articleService) {
    this.articleService = articleService;
  }

  //查询到的文章
  private Article article;
  //传入的文章的setter方法
  public void setArticle(Article article) {
    this.article = article;
  }
  //获取文章的getter方法
  public Article getArticle() {
    return article;
  }

  //传入的文章ID
  private String articleid;
  //传入文章ID的setter方法
  public void setArticleid(String articleid) {
    this.articleid = articleid;
  }

  /* --------------------------图片上传相关属性 begin--------------------------------- */
  //文件域对应的的文件内容
  private File file;
  //文件域对应的文件内容的setter方法
  public void setFile(File file) {
    this.file = file;
  }

  //文件域对应的文件名
  private String fileFileName;
  //文件域对应的文件名的setter方法
  public void setFileFileName(String fileFileName) {
    this.fileFileName = fileFileName;
  }

  //文件域对应的文件类型
  private String fileContentType;
  //文件域对应的文件类型的setter方法
  public void setFileContentType(String fileContentType) {
    this.fileContentType = fileContentType;
  }

  //直接在struts.xml文件中配置的属性：文章图片临时保存路径，与redactor.js中的变量名对应
  private String filelink;
  //接收struts.xml文件配置值的方法：设置文章图片临时保存路径
  public void setFilelink(String filelink) {
    this.filelink = filelink;
  }
  //返回文章图片临时保存路径:文件路径+文件名
  public String getFilelink() {
    return filelink;
  }

  //直接在struts.xml文件中配置的属性：文章图片正式保存路径，用于将文章图片从临时保存路径拷贝到正式保存路径
  private String articleImgPath;
  //接收struts.xml文件配置值的方法：设置文章图片正式保存路径
  public void setArticleImgPath(String articleImgPath) {
    this.articleImgPath = articleImgPath;
  }

  /* --------------------------------图片上传相关属性 end------------------------------------- */

  //处理请求：查询文章
  public String queryArticle() throws Exception {
    //调用业务逻辑组件
    article = articleService.queryArticle(articleid);
    return SUCCESS;
  }

  //处理请求：新增文章
  public String addArticle() throws Exception {
    //调用业务逻辑组件
    articleService.addArticle(article, filelink, articleImgPath);
    return SUCCESS;
  }

  //处理请求：修改文章
  public String updateArticle() throws Exception {
    //调用业务逻辑组件
    articleService.updateArticle(article, filelink, articleImgPath);
    return SUCCESS;
  }

  //处理请求：删除文章
  public String deleteArticle() throws Exception {
    //调用业务逻辑组件
    articleService.deleteArticle(articleid, articleImgPath);
    return SUCCESS;
  }

  //处理请求：保存文章图片
  public String saveArticleImage() throws Exception {
    //调用业务逻辑组件
    //修改文件名
    String filePattern = fileFileName.substring(fileFileName.lastIndexOf("."));
    fileFileName = UUID.randomUUID().toString() + filePattern;
    //文件路径加文件名
    filelink = filelink  + "/" + fileFileName;
    //以文件保存路径和文件名建立上传文件输出流
    String realPath = ServletActionContext.getServletContext().getRealPath(filelink);
    FileOutputStream fileOutputStream = new FileOutputStream(realPath);
    FileInputStream fileInputStream = new FileInputStream(file);
    byte[] buffer = new byte[1024];
    int len = 0;
    while((len=fileInputStream.read(buffer))>0) {
      fileOutputStream.write(buffer, 0, len);
    }
    fileOutputStream.close();
    fileInputStream.close();
    return SUCCESS;
  }
}
