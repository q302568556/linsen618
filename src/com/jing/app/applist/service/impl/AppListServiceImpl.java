package com.jing.app.applist.service.impl;

import com.jing.app.applist.dao.AppListDao;
import com.jing.app.applist.service.AppListService;
import com.jing.app.common.entity.App;
import com.jing.app.common.entity.Applink;

import java.util.List;

/**
 * Created by Jing on 2015/3/19.
 */
public class AppListServiceImpl implements AppListService {
  //DAO组件
  private AppListDao appListDao;
  //依赖注入DAO组件所需的setter方法
  public void setAppListDao(AppListDao appListDao) {
    this.appListDao = appListDao;
  }

  //查询应用列表
  public List<App> queryAppList() {
    //调用Dao组件的方法来实现业务逻辑
    List<App> appList = appListDao.queryAppList();
    return appList;
  }

  //查询应用链接
  public List<Applink> queryApplink(String appid) {
    //调用Dao组件的方法来实现业务逻辑
    List<Applink> applinkList = appListDao.queryApplink(appid);
    return applinkList;
  }
}
