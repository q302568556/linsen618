package com.jing.app.applist.dao.impl;

import com.jing.app.applist.dao.AppListDao;
import com.jing.app.common.entity.App;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jing on 2015/3/19.
 */
public class AppListDaoImpl extends HibernateDaoSupport implements AppListDao {

  //查询应用列表
  public List<App> queryAppList() {
    List<App> appList = new ArrayList<App>();
    String queryAppListHql = "from App";
    appList = (List<App>)getHibernateTemplate().find(queryAppListHql);
    return appList;
  }
}
