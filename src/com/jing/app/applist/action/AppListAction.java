package com.jing.app.applist.action;

import com.jing.app.applist.service.AppListService;
import com.jing.app.common.entity.App;
import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jing on 2015/3/19.
 */
public class AppListAction extends ActionSupport{
  //系统的业务逻辑组件
  private AppListService appListService;
  //设置注入业务逻辑组件的setter方法
  public void setAppListService(AppListService appListService) {
    this.appListService = appListService;
  }

  //直接在struts.xml中配置的属性：应用图片保存路径
  private String appImgPath;
  //接收struts.xml文件配置值的方法：设置应用图片保存路径
  public void setAppImgPath(String appImgPath) {
    this.appImgPath = appImgPath;
  }
  //返回应用图片临时保存路径
  public String getAppImgPath() {
    return appImgPath;
  }

  //查询出的应用列表
  private List<App> appList = new ArrayList<App>();
  //应用列表的getter方法
  public List<App> getAppList() {
    return appList;
  }

  //处理请求：查询应用列表
  public String queryAppList() throws Exception {
    //调用业务逻辑组件
    appList = appListService.queryAppList();
    return SUCCESS;
  }
}
