package com.jing.app.applist.service;

import com.jing.app.common.entity.App;

import java.util.List;

/**
 * Created by Jing on 2015/3/19.
 */
public interface AppListService {
  //查询应用列表
  public List<App> queryAppList();
}
