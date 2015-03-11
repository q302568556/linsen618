package com.jing.app.articlelist.action;

import com.jing.app.articlelist.service.ArticleListService;
import com.jing.app.common.entity.Article;
import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.List;

public class ArticleListAction extends ActionSupport {
	//系统的业务逻辑组件
	private ArticleListService articleListService;
	//设置注入业务逻辑组件的setter方法
	public void setArticleListService(ArticleListService articleListService) {
		this.articleListService = articleListService;
	}

  //获取到的文章列表
  private List<Article> articleList = new ArrayList<Article>();
  //获取文章列表的getter方法
  public List<Article> getArticleList() {
    return articleList;
  }

  //传入选择的日期
  private String selectedDate;
  //传入选择的日期的setter方法
  public void setSelectedDate(String selectedDate) {
    this.selectedDate = selectedDate;
  }

  //处理请求：获得文章列表
	public String queryArticleList() throws Exception{
		//调用业务逻辑组件
	  articleList = articleListService.queryArticleList(selectedDate);
		return SUCCESS;
	}
}
