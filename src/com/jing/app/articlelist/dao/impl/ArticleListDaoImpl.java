package com.jing.app.articlelist.dao.impl;

import com.jing.app.articlelist.dao.ArticleListDao;
import com.jing.app.common.entity.Article;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.ArrayList;
import java.util.List;

public class ArticleListDaoImpl extends HibernateDaoSupport implements ArticleListDao {
	
	//查询文章列表
	public List<Article> queryArticleList(String selectedDate) {
		List<Article> articleList = new ArrayList<Article>();
    String queryAritcleListHql = "from Vw_articlelist";
    if(null!=selectedDate && !"".equals(selectedDate)) {
        queryAritcleListHql += " where articledate='" + selectedDate + "'";
    }
    queryAritcleListHql += " order by articledate desc";
		articleList = (List<Article>)getHibernateTemplate()
                .find(queryAritcleListHql);
    return articleList;
	}
}
