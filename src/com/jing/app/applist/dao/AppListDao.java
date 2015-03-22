package com.jing.app.applist.dao;

import com.jing.app.common.entity.App;
import com.jing.app.common.entity.Applink;

import java.util.List;

/**
 * Created by Jing on 2015/3/19.
 */
public interface AppListDao {
  //查询应用列表
  public List<App> queryAppList();

  //查询应用链接
  public List<Applink> queryApplink(String appid);
}
