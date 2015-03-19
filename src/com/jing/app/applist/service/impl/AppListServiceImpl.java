package com.jing.app.applist.service.impl;

import com.jing.app.applist.dao.AppListDao;
import com.jing.app.applist.service.AppListService;
import com.jing.app.common.entity.App;

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
}
